import 'package:flutter/material.dart';

import './main_menu.dart';

class MainScrean extends StatefulWidget {
  @override
  MainScreanState createState() => MainScreanState();
}

class MainScreanState extends State<MainScrean> {

  Image appLogo = new Image(
      image: new ExactAssetImage("assets/images/logo.png"),
      height: 198.0,
      width: 20.0,
      alignment: FractionalOffset.centerRight);


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        // automaticallyImplyLeading: false,
        title: Text(
          "I Met You",
          style: new TextStyle(color: Colors.white,fontSize: 28,fontWeight: FontWeight.bold),

        ),
      ),
      body:
      Center(

      ),
      drawer:  MainMenu.getMainMenu(context),
      //MainMenu.getMainMenu(context)
    );
  }
}


