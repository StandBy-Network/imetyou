import 'package:flutter/material.dart';
import '../main_menu.dart';
import 'state_button_next.dart';
import '../../bean/layout_bean.dart';


class StateLayout {


  static Scaffold getScrean(LayoutBean layoutBean){
    Scaffold returnValue;
    returnValue = Scaffold(
      drawer: MainMenu.getMainMenu(layoutBean.context),
      appBar: AppBar(
        // automaticallyImplyLeading: false,
        title: Text(
          layoutBean.appBarTitle,
          style: new TextStyle(color: Colors.white,fontSize: 28,fontWeight: FontWeight.bold),
        ),
      ),
      body:
      Center(
        child: Column(
          children: <Widget>[
            Expanded(
              child: Container(
                color: Colors.blue,
                child: Row(
                  children: <Widget>[
                    Container(
                        width: 54,
                        color: Colors.blue,
                        child: IconButton(
                          icon: new Icon(Icons.arrow_back),
                          color: Colors.white,
                          onPressed: () {
                            Navigator.of(layoutBean.context).pop();
                          },
                        )
                    )   ,
                    Text(
                      layoutBean.headerTitle,
                      style: new TextStyle(color: Colors.white,fontSize: 20,fontWeight: FontWeight.bold),
                    ),

                  ],

                ),
              ),
            ),
            Expanded(
              flex: 10,

              child: Container(
                color: Colors.blue,
                padding: EdgeInsets.all(15.0),
// CONTENT ################################
                child: Container(
                  decoration: BoxDecoration(
                    color: Colors.white,
                    borderRadius: BorderRadius.circular(20.0),
                  ),
                  child: Column(
                    children: <Widget>[
                      Container(
                        margin: const EdgeInsets.all(15.0),
                        padding: const EdgeInsets.all(3.0),
                        decoration: BoxDecoration(
                          // border: Border.all(color: Colors.blueAccent)
                        ),
                        child: Container(
                          child:
                          Align(
                            alignment: Alignment.centerLeft,
                            child: Container(
                              //color: Colors.red,
                              child:
                              Text(
                                layoutBean.contengHeaderTitle,
                                style: new TextStyle(color: Colors.blue,fontSize: 18,fontWeight: FontWeight.bold),
                              ),
                            ),
                          ),



                        ),
                      ),
// BODY ###############################
                      Expanded(
                        child: Container(
                          width: 500,
                          child:layoutBean.body,
                        ),
                      ),
// BUTTON BAR #####################################
                      Container(
                        height: 50,
                        width: 160,
                        padding: EdgeInsets.fromLTRB(0, 0, 0, 10),
                        //child: StateButtonNext.getNextButton(context,new StateFormChronicIllness2(),"TOVÁBB"),
                        child: layoutBean.nextButton,
                      ),
                    ],
                  ),

                ),
              ),
            ),
          ],
        ),
      ),
    );
    return returnValue;
  }



}