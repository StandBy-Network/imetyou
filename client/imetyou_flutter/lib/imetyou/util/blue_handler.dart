import 'package:flutter_blue/flutter_blue.dart';

class BlueHandler {

  BlueHandler(){
    print("hello  blue");
  }

  test(){

    try{
      FlutterBlue flutterBlue = FlutterBlue.instance;
      print("decice found");

    }  catch(e) {

      print ("no blue decice found");
    }
    finally {
      print("hello  blue24");
    }



  }



}


