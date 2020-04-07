import 'package:flutter/material.dart';


class MainState extends StatefulWidget {
  @override
  MainStateState createState() => MainStateState();
}


class MainStateState  extends State<MainState> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Second Route"),
      ),
      body: Center(
        child: RaisedButton(
          onPressed: () {
            Navigator.pop(context);
          },
          child: Text('Go back!'),
        ),
      ),
    );
  }
}