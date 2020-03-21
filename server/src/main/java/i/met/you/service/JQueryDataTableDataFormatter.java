package i.met.you.service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import i.met.you.model.IBaseModel;
import i.met.you.model.view.IViewModel;
import i.met.you.service.view.IViewService;

/**
 * jQuery datatable json inputjat allitja elo illetve a rendezo, kereso
 * mukodeset kezeli le.
 * 
 * 
 *
 */
public class JQueryDataTableDataFormatter {

	private static final Logger logger = LoggerFactory.getLogger(JQueryDataTableDataFormatter.class);

	private String requestEncoding = null;

	/**
	 * Rekordok szoveges megformazoja. Ugyanabban a sorredben kell a rekordokat a
	 * listaban vissza adni, ahogy a <code>JQueryDataTableDataFormatter</code>
	 * konstruktoranak <code>columnOrder</code> parametereben meg lett hatarozva.
	 * 
	 * @author Csaba
	 *
	 * @param <T> Nezet vagy tabla entitas tipus
	 */
	public static interface IDataProvider<T> {
		List<String> mapAsString(T entity);
	}

	// private String requestEncoding;
	private String sEcho;
	private String iDisplayStart;
	private String iDisplayLength;
	private String iSortCol_0;
	private String sSortDir;
	private String queryString;
	private int start;
	private int max;
	private Boolean paramType = true;
	private List<Object> params = new ArrayList<>();

	public JQueryDataTableDataFormatter(HttpServletRequest request, Map<String, Class<?>> columnOrder, String tableName,
			String customWhereClause) throws UnsupportedEncodingException {
		super();
		// this.tableName = tableName;
		this.requestEncoding = request.getCharacterEncoding() != null ? request.getCharacterEncoding() : "UTF-8";
		this.sEcho = request.getParameter("sEcho");
		this.iDisplayStart = request.getParameter("iDisplayStart");
		this.iDisplayLength = request.getParameter("iDisplayLength");
		this.iSortCol_0 = request.getParameter("iSortCol_0");
		this.sSortDir = "asc".equalsIgnoreCase(request.getParameter("sSortDir_0")) ? "ASC" : "DESC"; // prevent
																										// injection
		// this.columnOrder = columnOrder;

		this.start = iDisplayStart != null ? Integer.valueOf(iDisplayStart) : -1;
		this.max = iDisplayLength != null ? Integer.valueOf(iDisplayLength) : -1;

		StringBuilder queryStringBuilder = new StringBuilder(" SELECT * FROM ");
		queryStringBuilder.append(tableName).append(" where 1=1  ");

		if (StringUtils.isNotEmpty(customWhereClause)) {
			queryStringBuilder.append(" AND ").append(customWhereClause).append(" ");
		}

		@SuppressWarnings("unchecked")
		Map.Entry<String, Class<?>>[] columnOrderTypeArray = (Map.Entry<String, Class<?>>[]) columnOrder.entrySet()
				.toArray(new Map.Entry[columnOrder.entrySet().size()]);

		for (int i = 0; i < columnOrderTypeArray.length; ++i) {
			String param = request.getParameter("sSearch_".concat(String.valueOf(i)));
			if (requestEncoding != null) {
				if(param!=null) {
					param = URLEncoder.encode(param, requestEncoding);
					param = URLDecoder.decode(param, "UTF-8");	
				}
				
			}

			logger.debug("search : " + param);
			if (StringUtils.isNotEmpty(param)) {
				param = param.trim().toLowerCase();
				if (Number.class.isAssignableFrom(columnOrderTypeArray[i].getValue())) {
					queryStringBuilder.append(" AND ").append(columnOrderTypeArray[i].getKey()).append(" = ? ");
					try {
						new BigDecimal(param);
					} catch (NumberFormatException name) {
						paramType = false;
					}
					if (paramType == true) {
						params.add(new BigDecimal(param));
					} else {
						params.add(new BigDecimal(-1));
					}
				} else if (String.class.equals(columnOrderTypeArray[i].getValue())) {
					queryStringBuilder.append(" AND LOWER(").append(columnOrderTypeArray[i].getKey())
							.append(") like lower(?) ");
					params.add("%".concat(param).concat("%"));
				}
			}
		}

		int sortCollIndex = 0;

		if (iSortCol_0 != null) {
			sortCollIndex = Integer.valueOf(iSortCol_0);
		}

		logger.debug("TABLE " + tableName);

		if ((start != -1) && iSortCol_0 != null) {
			if (Number.class.isAssignableFrom(columnOrderTypeArray[sortCollIndex].getValue())) {
				queryStringBuilder.append(" ORDER BY ").append(columnOrderTypeArray[sortCollIndex].getKey()).append(" ")
						.append(sSortDir);
			} else if (String.class.equals(columnOrderTypeArray[sortCollIndex].getValue())) {
				queryStringBuilder.append(" ORDER BY LOWER(").append(columnOrderTypeArray[sortCollIndex].getKey())
						.append(") ").append(sSortDir);
			}
		} else {
			queryStringBuilder.append(" ORDER BY ").append(columnOrderTypeArray[0].getKey()).append(" ")
					.append(sSortDir);
		}

		this.queryString = queryStringBuilder.toString();
		logger.debug(queryString);
	}

	/**
	 * Konstruktor.
	 * 
	 * @param request     jQuery datatable inputokat tartalmazo request
	 * @param columnOrder az oszlopok megjelenitesi sorrendje, annak tipusaval.
	 *                    <b>Fontos, hogy a mapnek sorrend tartonak kell lennie!</b>
	 * @param tableName   az adatbazis tablaneve
	 * @throws UnsupportedEncodingException a request parametereit utf-8-ra probalja
	 *                                      konvertalni,de ha input encoding nem
	 *                                      ismert hibat dob.
	 */
	public JQueryDataTableDataFormatter(HttpServletRequest request, Map<String, Class<?>> columnOrder, String tableName)
			throws UnsupportedEncodingException {
		this(request, columnOrder, tableName, "");
	}

	/**
	 * Nezet tablak adatait formazza meg
	 * 
	 * @param viewService adott nezet entitas szerviz osztalya
	 * @param dataMapper  mezok szoveges megformazasat valositja meg
	 * @return jQuery Data table vezerlo JSON
	 * @throws JsonProcessingException JSON atalakitas soran keletkezett hiba
	 */
	public <T extends IViewModel> String formatView(IViewService<T> viewService, IDataProvider<T> dataMapper)
			throws JsonProcessingException {
		Long queryCount = viewService.queryCount(queryString, params);
		List<T> foundEntities = viewService.findAll(queryString, params, start, max);

		return doCommonFormatting(queryCount, foundEntities, dataMapper);

	}

	/**
	 * Altalanos tablak adatait formazza meg
	 * 
	 * @param service    adott T tipusu tabla szerviz osztalya
	 * @param dataMapper mezok szoveges megformazasat valositja meg
	 * @return jQuery Data table vezerlo JSON
	 * @throws JsonProcessingException JSON atalakitas soran keletkezett hiba
	 */
	public <T extends IBaseModel> String formatTable(IBaseService<T> service, IDataProvider<T> dataMapper)
			throws JsonProcessingException {
		Long queryCount = service.queryCount(queryString);
		List<T> foundEntities = service.findAll(queryString, start, max);

		return doCommonFormatting(queryCount, foundEntities, dataMapper);
	}

	/**
	 * Nezet es altalanos tabla formazast valositja meg (rekordokbol mezok sztring
	 * formazasa)
	 * 
	 * @param queryCount a nezet tabla keresesi eredmenyeinek szama
	 * @param result     a nezet tabla adatai rekordok formajaban
	 * @param dataMapper rekord mezoinek sztring formazoja
	 * @return jQuery Data table vezerlo JSON
	 * @throws JsonProcessingException JSON atalakitas soran keletkezett hiba
	 */
	private <T> String doCommonFormatting(Long queryCount, List<T> result, IDataProvider<T> dataMapper)
			throws JsonProcessingException {

		LinkedList<List<String>> dataTableRows = new LinkedList<>();

		for (Iterator<T> i = result.iterator(); i.hasNext();) {
			dataTableRows.add(dataMapper.mapAsString(i.next()));
		}

		HashMap<String, Object> map = new HashMap<String, Object>();
		ObjectMapper objectMapper = new ObjectMapper();

		map.put("sEcho", sEcho);
		map.put("iTotalRecords", queryCount);
		map.put("iTotalDisplayRecords", queryCount);
		map.put("aaData", dataTableRows);

		return objectMapper.writeValueAsString(map);
	}
}
