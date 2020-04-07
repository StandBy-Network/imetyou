import 'package:flutter/material.dart';
import '../util/settings_handler.dart';
import '../ui/state_ui/state_main.dart';
import '../ui/state_ui/state_form_born_1.dart';



class MainMenu {

  static Drawer getMainMenu(BuildContext context){
    TextStyle MENU_TEXT_STILE = new TextStyle(color: Colors.black,fontSize: 20,fontWeight: FontWeight.bold);
    double ICON_SIZE =30;
    Drawer returnValue;
    returnValue=Drawer(
      child: ListView(
        padding: EdgeInsets.zero,
        children: <Widget>[
          DrawerHeader(

            decoration: BoxDecoration(
                color: Colors.blue,
                image: DecorationImage(
                    fit: BoxFit.fill,
                    image: AssetImage('assets/images/cover.jpg'))),
          ),
          ListTile(
            leading: Icon(Icons.person,size: ICON_SIZE),
            title: Text(
                'Státuszom',
                style: MENU_TEXT_STILE
            ),
            onTap:() {
              if(SettingsHandler.hasCurrentState()){
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => MainState()),
                );
              }else{
                Navigator.of(context).pop();
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => StateFormBorn1()),
                );
              }
            },
          ),
          ListTile(
            leading: Icon(Icons.send,size: ICON_SIZE),
            title: Text('Érintettek listája',
                style: MENU_TEXT_STILE
            ),
            onTap:() => print("Érintettek listája"),
          ),
          ListTile(
            leading: Icon(Icons.flag,size: ICON_SIZE),
            title: Text('Barátaim státusza',
                style: MENU_TEXT_STILE
            ),
            onTap:() => print("barátaim státusza"),
          ),
          ListTile(
            leading: Icon(Icons.report,size: ICON_SIZE),
            title: Text('Statisztikai szerviz',
                style: MENU_TEXT_STILE
            ),
            onTap:() => print("Statisztikai szerviz"),
          ),
        ],
      ),
    );
    return returnValue;
  }
}