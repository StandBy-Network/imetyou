import 'package:flutter/material.dart';

class LayoutBean {


  BuildContext _context = null;
  String _appBarTitle = "";
  String _headerTitle = "";
  String _contengHeaderTitle = "";
  Container _body = null;
  RaisedButton _nextButton = null;

  BuildContext get context => _context;

  set context(BuildContext value) {
    _context = value;
  }

  String get appBarTitle => _appBarTitle;

  set appBarTitle(String value) {
    _appBarTitle = value;
  }

  RaisedButton get nextButton => _nextButton;

  set nextButton(RaisedButton value) {
    _nextButton = value;
  }

  Container get body => _body;

  set body(Container value) {
    _body = value;
  }

  String get contengHeaderTitle => _contengHeaderTitle;

  set contengHeaderTitle(String value) {
    _contengHeaderTitle = value;
  }

  String get headerTitle => _headerTitle;

  set headerTitle(String value) {
    _headerTitle = value;
  }
}
