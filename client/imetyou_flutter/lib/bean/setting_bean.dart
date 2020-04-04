import 'package:json_annotation/json_annotation.dart';


part 'setting_bean.g.dart';

@JsonSerializable()
class SettingBean{


  SettingBean(this.name);

  @JsonKey(name: "name")
  String name;


  factory SettingBean.fromJson(Map<String, dynamic> json) => _$SettingBeanFromJson(json);
  Map<String, dynamic> toJson() => _$SettingBeanToJson(this);


}