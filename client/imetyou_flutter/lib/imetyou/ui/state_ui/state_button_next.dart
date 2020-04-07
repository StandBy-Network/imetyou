import 'package:flutter/material.dart';



class StateButtonNext{

  static RaisedButton getNextButton(BuildContext context,Widget next,String title){
    RaisedButton returnValue;
    returnValue = RaisedButton(
      shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.all(Radius.circular(10.0))),
      onPressed: (){
        Navigator.push(
            context,
            MaterialPageRoute(builder: (context) => next),
        );

      },
      textColor: Colors.white,
      color: Colors.pink,
      padding: EdgeInsets.fromLTRB(5, 0, 5, 0),
      child: Padding(
        padding: EdgeInsets.fromLTRB(0,0,0,0),
        child: Row(
          crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
            Container(
              color: Colors.pink,
              padding: EdgeInsets.fromLTRB(10, 4, 10, 4),
              child: Text(title,
                    style: TextStyle(color: Colors.white,fontSize: 20,fontWeight: FontWeight.bold)
              ),
            ),
            Padding(
              padding: EdgeInsets.fromLTRB(4, 0, 10, 0),
              child: Icon(
                Icons.arrow_forward,
                color:Colors.white,
                size: 30,
              ),
            ),
          ],
        ),
      ),
    );
    return returnValue;
  }


  static clickNext (BuildContext context,RaisedButton btn, Container next){



  }


}