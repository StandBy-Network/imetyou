import 'package:flutter/material.dart';

class MainScrean extends StatefulWidget {
  @override
  MainScreanState createState() => MainScreanState();
}

class MainScreanState extends State<MainScrean> {

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("k"),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              'Welcome',
            ),
          ],
        ),
      ),

    );
  }
}
