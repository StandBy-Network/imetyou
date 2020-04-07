import 'package:json_annotation/json_annotation.dart';


part 'application_settings.g.dart';

@JsonSerializable()
class ApplicationSetting{


  ApplicationSetting();

  @JsonKey(name: "name")
  String name;


  factory ApplicationSetting.fromJson(Map<String, dynamic> json) => _$ApplicationSettingFromJson(json);
  Map<String, dynamic> toJson() => _$ApplicationSettingToJson(this);


}