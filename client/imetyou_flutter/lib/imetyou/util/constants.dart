/*
import 'dart:ui' as dartUI;
import 'dart:convert'as dartConvert;
* */
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'dart:convert';



class Constants {

  static DateFormat DATE_FORMATTER_FULL = new DateFormat("yyyy.MM.dd. HH:mm:ss:SSS");
  static DateFormat DATE_FORMATTER_SECOND = new DateFormat("yyyy.MM.dd. HH:mm:ss");
  static DateFormat DATE_FORMATTER_SECOND_EDGE = new DateFormat("yyyy/MM/dd HH:mm:ss");
  static DateFormat DATE_FORMATTER_MINUTE = new DateFormat("yyyy.MM.dd. HH:mm");
  static DateFormat DATE_FORMATTER_HOUR = new DateFormat("yyyy.MM.dd. HH");
  static DateFormat DATE_FORMATTER_DAY = new DateFormat("yyyy.MM.dd");
  static DateFormat DATE_FORMATTER_DAY_NUMBER = new DateFormat("yyyyMMdd");
  static DateFormat DATE_FORMATTER_MONTH = new DateFormat("yyyy.MM");
  static DateFormat DATE_FORMATTER_YEAR = new DateFormat("yyyy");
  static DateFormat DATE_FORMATTER_DIRECTORY = new DateFormat("yyyy/MM/dd/");
  static DateFormat DATE_FORMATTER_FILENAME = new DateFormat("yyyy_MM_dd");
  static DateFormat DATE_FORMATTER_EXPORT_COMPARE = new DateFormat("dd.MM.yyyy");
  static DateFormat DATE_FORMATTER_EXPORT_COMPARE_NAT = new DateFormat("dd/MM/yyyy");
  static DateFormat DATE_FORMATTER_EXPORT_FILENAME = new DateFormat("yyMMdd");
  static DateFormat DATE_FORMATTER_JUSTHOURMIN = new DateFormat("HH:mm");
  static DateFormat DATE_FORMATTER_HOURMIN_NUMBER = new DateFormat("HHmm");
  static DateFormat DATE_FORMATTER_SECOND_FILE_NAME = new DateFormat("yyyy_MM_dd_HH_mm_ss");
  static DateFormat DATE_FORMATTER_EXPORT_PLASMA = new DateFormat("MM/dd/yyyy");
  static DateFormat DATE_FORMATTER_SECOND_UND = new DateFormat("yyyy-MM-dd HH:mm:ss");
  static DateFormat DATE_FORMATTER_POSTGRE_DATE = new DateFormat("yyyy-MM-dd");


  static String formatDate(DateTime date, DateFormat format) {
  String value = "";
  value = format.format(date);
  return value;
  }


  static String encodeBase64URL(String text){
    String returnValue;
    returnValue = base64Url.encode(utf8.encode(text));
    return returnValue;
  }

  static String decodeBase64URL(String text){
    String returnValue;
    returnValue = utf8.decode(base64Url.decode(text));
    return returnValue;
  }

}
