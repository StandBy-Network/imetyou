import 'package:flutter/material.dart';
import '../main_menu.dart';
import 'state_button_next.dart';
import 'state_form_health3.dart';
import '../../bean/layout_bean.dart';
import 'state_layout.dart';
import 'package:custom_radio/custom_radio.dart';

class StateFormChronicIllness2 extends StatefulWidget {
  @override
  StateFormChronicIllness2State createState() => StateFormChronicIllness2State();
}

class StateFormChronicIllness2State  extends State<StateFormChronicIllness2> {

  Container c = Container(
    child: Column(
      children:  <Widget>[
    new Radio(
    value: 0,
      groupValue: 1,

      //onChanged: _handleRadioValueChange,
    ),
      new Radio(
        value: 1,
        groupValue: 2,
        //onChanged: _handleRadioValueChange,
      ),

      ]


    ),
  );

  @override
  Widget build(BuildContext context) {
    LayoutBean layoutBean = new LayoutBean();
    layoutBean.context = context;
    layoutBean.appBarTitle = "Státuszom";
    layoutBean.headerTitle = "ADATOK MEGADÁSA";
    layoutBean.contengHeaderTitle = "VAN VALAMI KRÓNIKUS BETEGSÉGE?";
    layoutBean.body = c;
    layoutBean.nextButton = StateButtonNext.getNextButton(
        context, new StateFormChronicIllness2(), "TOVÁBB");
    return StateLayout.getScrean(layoutBean);
  }
}