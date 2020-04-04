import 'dart:ui';

 Map<int, Color> _color =
{
  50:Color.fromRGBO(13,22,94, .1),
  100:Color.fromRGBO(13,22,94, .2),
  200:Color.fromRGBO(13,22,94, .3),
  300:Color.fromRGBO(13,22,94, .4),
  400:Color.fromRGBO(13,22,94, .5),
  500:Color.fromRGBO(13,22,94, .6),
  600:Color.fromRGBO(13,22,94, .7),
  700:Color.fromRGBO(13,22,94, .8),
  800:Color.fromRGBO(13,22,94, .9),
  900:Color.fromRGBO(13,22,94, 1),
};

class Constants {

  Constants();


  static Map getColor(){
    return _color;
  }

}
