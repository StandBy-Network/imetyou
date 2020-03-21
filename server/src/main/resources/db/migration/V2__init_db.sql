--CREATE SCHEMA imetyou;

CREATE TABLE imetyou._template ( 
	id                   text  NOT NULL ,
	created              timestamp DEFAULT now() NOT NULL ,
	creator              text  NOT NULL ,
	modified             timestamp DEFAULT now() NOT NULL ,
	modifier             text  NOT NULL ,
	deleted_10           integer DEFAULT 0  ,
	"version"            integer DEFAULT 1  ,
	CONSTRAINT pk__template_id PRIMARY KEY ( id )
 );

COMMENT ON COLUMN imetyou._template.id IS 'Azonosító';

COMMENT ON COLUMN imetyou._template.created IS '-Létrehozás dátuma-';

COMMENT ON COLUMN imetyou._template.creator IS 'Rekord létrehozójának felhasználóneve';

COMMENT ON COLUMN imetyou._template.modified IS 'Rekord utolsó módosításának időpontja';

COMMENT ON COLUMN imetyou._template.modifier IS 'Rekord módosítójának felhasználóneve';

COMMENT ON COLUMN imetyou._template.deleted_10 IS 'Rekord logikai törölt-e';

COMMENT ON COLUMN imetyou._template."version" IS '-JPA optimistic locking-';

CREATE TABLE imetyou.md_attachment ( 
	id                   text  NOT NULL ,
	created              timestamp DEFAULT now() NOT NULL ,
	creator              text  NOT NULL ,
	modified             timestamp DEFAULT now() NOT NULL ,
	modifier             text  NOT NULL ,
	deleted_10           integer DEFAULT 0  ,
	"version"            integer DEFAULT 1  ,
	clazzname            varchar(255)   ,
	attachment_type_code varchar(255)   ,
	"comment"            varchar(255)   ,
	orig_file_name       varchar(100)   ,
	store_file_path      varchar(400)   ,
	store_file_name      varchar(255)   ,
	store_file_size      numeric   ,
	uploader_sys_username varchar(255)   ,
	user_id              text   ,
	verified10           inet   ,
	CONSTRAINT pk__template_id_4 PRIMARY KEY ( id )
 );

COMMENT ON TABLE imetyou.md_attachment IS 'Csatolmányok kezelérére szolgáló tábla.\n\nCsatolmány technikailag bármely entitáshoz tetszőleges számban feltölthető.\nA csatolmánynak az entitáshoz való kapcsolatát az adott entitás uuid-ja (globális egyedi azonosítója) határozza meg.';

COMMENT ON COLUMN imetyou.md_attachment.id IS 'Azonosító';

COMMENT ON COLUMN imetyou.md_attachment.created IS '-Létrehozás dátuma-';

COMMENT ON COLUMN imetyou.md_attachment.creator IS 'Rekord létrehozójának felhasználóneve';

COMMENT ON COLUMN imetyou.md_attachment.modified IS 'Rekord utolsó módosításának időpontja';

COMMENT ON COLUMN imetyou.md_attachment.modifier IS 'Rekord módosítójának felhasználóneve';

COMMENT ON COLUMN imetyou.md_attachment.deleted_10 IS 'Rekord logikai törölt-e';

COMMENT ON COLUMN imetyou.md_attachment."version" IS '-JPA optimistic locking-';

COMMENT ON COLUMN imetyou.md_attachment.clazzname IS 'clazzName\n#{"fieldLabel":"clazzName:", "fieldI18n": "field.vpcSysAttachment.clazzName", "groupId": "", "showOnForm" : "true"}#';

COMMENT ON COLUMN imetyou.md_attachment.attachment_type_code IS 'Csatolmány típusa\n#{"fieldLabel":"Csatolmány típusa", "fieldI18n": "field.vpcSysAttachment.attachment_type_cd", "groupId": "ATTACHMENT_TYPE", "showOnForm" : "true"}#';

COMMENT ON COLUMN imetyou.md_attachment."comment" IS 'A feltöltéshez tartozó megjegyzés arra az esetre ha a feltöltött file nem tipizálható valamilyen törzsadatból.\n#{"fieldLabel":"A feltöltéshez tartozó megjegyzés arra az esetre, ha a feltöltött file nem tipizálható valamilyen törzsadatból:", "fieldI18n": "field.vpcSysAttachment.comment", "groupId": "", "showOnForm" : "true"}#';

COMMENT ON COLUMN imetyou.md_attachment.orig_file_name IS 'A feltöltött file eredeti neve\n#{"fieldLabel":"A feltöltött file eredeti neve:", "fieldI18n": "field.vpcSysAttachment.origFileName", "groupId": "", "showOnForm" : "true"}#';

COMMENT ON COLUMN imetyou.md_attachment.store_file_path IS 'A feltöltött file tárolási útvonala. Ez mindig relatív útvonal.\nA gyökér útvonal az alkalmazás beállítások táblában kerül meghatározásra.\n#{"fieldLabel":"A feltöltött file tárolási útvonala:", "fieldI18n": "field.vpcSysAttachment.storeFilePath", "groupId": "", "showOnForm" : "true"}#';

COMMENT ON COLUMN imetyou.md_attachment.store_file_name IS 'A feltöltött file tárolási neve. Ezt egy generált uuid-val tároljuk, hogy a fájlrendszerben lévő névütközést elkerüljük.\n#{"fieldLabel":"A feltöltött file tárolási neve:", "fieldI18n": "field.vpcSysAttachment.storeFileName", "groupId": "", "showOnForm" : "true"}#';

COMMENT ON COLUMN imetyou.md_attachment.store_file_size IS 'A feltöltött fájl mérete byte-ban\n#{"fieldLabel":"A feltöltött fájl mérete byte-ban:", "fieldI18n": "field.vpcSysAttachment.storeFileSize", "groupId": "", "showOnForm" : "true"}#';

COMMENT ON COLUMN imetyou.md_attachment.uploader_sys_username IS 'A fájl feltöltőjének felhasználóneve.\n#{"fieldLabel":"A fájl feltöltőjének felhasználóneve:", "fieldI18n": "field.vpcSysAttachment.uploaderUsername", "groupId": "", "showOnForm" : "true"}#';

COMMENT ON COLUMN imetyou.md_attachment.user_id IS 'A csatolmány tulajdonosának azonosítója';

COMMENT ON COLUMN imetyou.md_attachment.verified10 IS 'A dokumentum ellenőrzött-e';

CREATE TABLE imetyou.md_collection ( 
	id                   text  NOT NULL ,
	created              timestamp DEFAULT now() NOT NULL ,
	creator              varchar(255)  NOT NULL ,
	modified             timestamp DEFAULT now() NOT NULL ,
	modifier             varchar(255)  NOT NULL ,
	deleted_10           integer DEFAULT 0  ,
	"version"            integer DEFAULT 1  ,
	md_group             varchar(255)   ,
	md_code              varchar(255)   ,
	md_group_name        varchar(255)   ,
	md_group_description text   ,
	md_group_i18n        varchar(255)   ,
	md_code_name         varchar(255)   ,
	md_code_description  text   ,
	md_code_i18n         varchar(255)   ,
	md_value             varchar(4000)   ,
	CONSTRAINT pk__template_id_1 PRIMARY KEY ( id )
 );

COMMENT ON TABLE imetyou.md_collection IS 'Általános törzsadatok tárolására szolgál.\nA törzsatatokat kategóriákba szervezzük a md_group mezőben és a törzsadatot kóddal látjuk el az md_code mezőben.\nAz id az md_group és az md_code mezők ponttal történő konkatenációjának url safe base64 értéke. \nEz biztosítja, hogy a csoporton belül a code - nak egyedinek kell lennie.  A csoport és az md_group és md_code mezők értéke a létrehozás után nem változtathatóak. Változtatás csak az adott törzsadat logikai törlésével és új törzsadat beszúrásával lehetségesek.';

COMMENT ON COLUMN imetyou.md_collection.id IS 'Azonosító\n#{"fieldLabel":"Azonosító", "fieldI18n": "field.common.id", "groupId": "", "showOnForm" : "false"}#';

COMMENT ON COLUMN imetyou.md_collection.created IS '-Létrehozás dátuma-\n#{"fieldLabel":"Létrehozás dátuma", "fieldI18n": "field.common.created", "groupId": "", "showOnForm" : "false"}#';

COMMENT ON COLUMN imetyou.md_collection.creator IS 'Rekord létrehozójának felhasználóneve\n#{"fieldLabel":"Létrehozta", "fieldI18n": "field.common.creator", "groupId": "", "showOnForm" : "false"}#';

COMMENT ON COLUMN imetyou.md_collection.modified IS 'Rekord utolsó módosításának időpontja\n#{"fieldLabel":"Módosítás dátuma", "fieldI18n": "field.common.modified", "groupId": "", "showOnForm" : "false"}#';

COMMENT ON COLUMN imetyou.md_collection.modifier IS 'Rekord módosítójának felhasználóneve\n#{"fieldLabel":"Módosította", "fieldI18n": "field.common.modifier", "groupId": "", "showOnForm" : "false"}#';

COMMENT ON COLUMN imetyou.md_collection.deleted_10 IS 'Rekord logikai törölt-e\n#{"fieldLabel":"Törölt", "fieldI18n": "field.common.deleted10", "groupId": "", "showOnForm" : "false"}#';

COMMENT ON COLUMN imetyou.md_collection."version" IS '-JPA optimistic locking-\n#{"fieldLabel":"-JPA optimistic locking-", "fieldI18n": "field.common.version", "groupId": "", "showOnForm" : "false"}#';

COMMENT ON COLUMN imetyou.md_collection.md_group IS 'Törzsadat csoportosítására szolgál.';

COMMENT ON COLUMN imetyou.md_collection.md_code IS 'Törzsadat csoporton belüli egyedi meghatározásra szolgál.';

COMMENT ON COLUMN imetyou.md_collection.md_group_name IS 'Törzsadatcsoport rövid leírása.';

COMMENT ON COLUMN imetyou.md_collection.md_group_description IS 'Törzsadat csoport leírása';

COMMENT ON COLUMN imetyou.md_collection.md_group_i18n IS 'Törzsadatcsoport nyelvesített megjelenítését szolgálja. Értéke a nyelvi elem kulcsa.';

COMMENT ON COLUMN imetyou.md_collection.md_code_name IS 'Törzsadat elem rövid leírása';

COMMENT ON COLUMN imetyou.md_collection.md_code_description IS 'Törzsadat részletes leírása';

COMMENT ON COLUMN imetyou.md_collection.md_code_i18n IS 'Törzsadat nyelvesített megjelenítését szolgálja. Értéke a nyelvi elem kulcsa.';

COMMENT ON COLUMN imetyou.md_collection.md_value IS 'Törzsadat értéke';

CREATE TABLE imetyou.sys_application_settings ( 
	id                   text  NOT NULL ,
	created              timestamp DEFAULT now() NOT NULL ,
	creator              varchar(255)  NOT NULL ,
	modified             timestamp DEFAULT now() NOT NULL ,
	modifier             text  NOT NULL ,
	deleted_10           integer DEFAULT 0  ,
	"version"            integer DEFAULT 1  ,
	mail_smtp_starttls_enable_10 integer DEFAULT 1  ,
	mail_smtp_host       varchar(255)   ,
	mail_smtp_port       varchar(255) DEFAULT 587  ,
	mail_from            varchar(255)   ,
	mail_from_name       varchar(255)   ,
	mail_smtp_auth_10    integer DEFAULT 1  ,
	mail_user            varchar(255)   ,
	mail_password        varchar(255)   ,
	google_api_key       varchar(255)   ,
	application_version  varchar(255) DEFAULT '1.1.0'  ,
	application_environment varchar(255) DEFAULT 'DEV'  ,
	file_store_path      varchar(400)   ,
	default_system_language varchar(255) DEFAULT 'EN'  ,
	CONSTRAINT pk__template_id_8 PRIMARY KEY ( id )
 );

COMMENT ON COLUMN imetyou.sys_application_settings.id IS 'Azonosító';

COMMENT ON COLUMN imetyou.sys_application_settings.created IS '-Létrehozás dátuma-';

COMMENT ON COLUMN imetyou.sys_application_settings.creator IS 'Rekord létrehozójának felhasználóneve';

COMMENT ON COLUMN imetyou.sys_application_settings.modified IS 'Rekord utolsó módosításának időpontja';

COMMENT ON COLUMN imetyou.sys_application_settings.modifier IS 'Rekord módosítójának felhasználóneve';

COMMENT ON COLUMN imetyou.sys_application_settings.deleted_10 IS 'Rekord logikai törölt-e';

COMMENT ON COLUMN imetyou.sys_application_settings."version" IS '-JPA optimistic locking-';

COMMENT ON COLUMN imetyou.sys_application_settings.mail_smtp_starttls_enable_10 IS 'Secure connection enabled?\nTLS protocol setting. (GMAIL requires) (TRUE|FALSE)';

COMMENT ON COLUMN imetyou.sys_application_settings.mail_smtp_host IS 'SMTP server host name or IP';

COMMENT ON COLUMN imetyou.sys_application_settings.mail_smtp_port IS 'SMTP Port of the mail server';

COMMENT ON COLUMN imetyou.sys_application_settings.mail_from IS 'Sender email address';

COMMENT ON COLUMN imetyou.sys_application_settings.mail_from_name IS 'Display name of the sender';

COMMENT ON COLUMN imetyou.sys_application_settings.mail_smtp_auth_10 IS 'SMTP authentication required? (TRUE|FALSE)';

COMMENT ON COLUMN imetyou.sys_application_settings.mail_user IS 'Mail server user name';

COMMENT ON COLUMN imetyou.sys_application_settings.mail_password IS 'Mail server password';

COMMENT ON COLUMN imetyou.sys_application_settings.google_api_key IS 'Google Maps API key';

COMMENT ON COLUMN imetyou.sys_application_settings.application_version IS 'VERSION OF THE SYSTEM';

COMMENT ON COLUMN imetyou.sys_application_settings.application_environment IS 'DEV = Development, TEST = Testing, PROD = Production';

COMMENT ON COLUMN imetyou.sys_application_settings.file_store_path IS 'A videok és képek fájlrendszerbeli helye';

COMMENT ON COLUMN imetyou.sys_application_settings.default_system_language IS 'Alapértelmezett rendszer nyelv';

CREATE TABLE imetyou.sys_role ( 
	id                   text  NOT NULL ,
	created              timestamp DEFAULT now() NOT NULL ,
	creator              text  NOT NULL ,
	modified             timestamp DEFAULT now() NOT NULL ,
	modifier             text  NOT NULL ,
	deleted_10           integer DEFAULT 0  ,
	"version"            integer DEFAULT 1  ,
	role_code            varchar(255)   ,
	role_name            varchar(255)   ,
	role_description     text   ,
	role_group           varchar(255)   ,
	CONSTRAINT pk__template_id_2 PRIMARY KEY ( id )
 );

COMMENT ON TABLE imetyou.sys_role IS 'Felhasználói szerepek tárolására szolgál';

COMMENT ON COLUMN imetyou.sys_role.id IS 'Azonosító';

COMMENT ON COLUMN imetyou.sys_role.created IS '-Létrehozás dátuma-';

COMMENT ON COLUMN imetyou.sys_role.creator IS 'Rekord létrehozójának felhasználóneve';

COMMENT ON COLUMN imetyou.sys_role.modified IS 'Rekord utolsó módosításának időpontja';

COMMENT ON COLUMN imetyou.sys_role.modifier IS 'Rekord módosítójának felhasználóneve';

COMMENT ON COLUMN imetyou.sys_role.deleted_10 IS 'Rekord logikai törölt-e';

COMMENT ON COLUMN imetyou.sys_role."version" IS '-JPA optimistic locking-';

COMMENT ON COLUMN imetyou.sys_role.role_code IS 'Szerep kódja.';

COMMENT ON COLUMN imetyou.sys_role.role_name IS 'A felhasználói szerep rögid leírása';

COMMENT ON COLUMN imetyou.sys_role.role_description IS 'Felhasználói szerep részletes leírása';

COMMENT ON COLUMN imetyou.sys_role.role_group IS 'szerepek csoportosítása.';

CREATE TABLE imetyou.sys_user ( 
	id                   text  NOT NULL ,
	created              timestamp DEFAULT now() NOT NULL ,
	creator              text  NOT NULL ,
	modified             timestamp DEFAULT now() NOT NULL ,
	modifier             text  NOT NULL ,
	deleted_10           integer DEFAULT 0  ,
	date_of_deletion     timestamp   ,
	"version"            integer DEFAULT 1 NOT NULL ,
	user_email           varchar(255)  NOT NULL ,
	user_password        varchar(255)  NOT NULL ,
	user_role            varchar(255)  NOT NULL ,
	user_title           varchar(255)   ,
	user_name            varchar(255)   ,
	user_family_name     varchar(255)   ,
	user_given_name      varchar(255)   ,
	user_gender_mdci     text   ,
	user_nickname        varchar(255)   ,
	user_phone           varchar(255)   ,
	user_location_lat    numeric   ,
	user_location_lon    numeric   ,
	CONSTRAINT pk__template_id_0 PRIMARY KEY ( id ),
	CONSTRAINT idx_user_email UNIQUE ( user_email ) 
 );

COMMENT ON TABLE imetyou.sys_user IS 'A felhasználók tárolására szolgál. Nem tesz különbséget a felhasználó típusa között a gyorsabb kiszolgálás érdekében.\nA felhasználó típusa a user_type menőben kerül meghatározásra.';

COMMENT ON COLUMN imetyou.sys_user.id IS 'Azonosító\nA felhasználó e-mail címéből képzett url és file safe base64 string';

COMMENT ON COLUMN imetyou.sys_user.created IS 'Létrehozás dátuma';

COMMENT ON COLUMN imetyou.sys_user.creator IS 'Rekord létrehozójának id-ja. Ez user id lesz';

COMMENT ON COLUMN imetyou.sys_user.modified IS 'Rekord utolsó módosításának időpontja.';

COMMENT ON COLUMN imetyou.sys_user.modifier IS 'Rekord módosítójának felhasználóneve.';

COMMENT ON COLUMN imetyou.sys_user.deleted_10 IS 'A rekord logikai töröltségét jelöli';

COMMENT ON COLUMN imetyou.sys_user.date_of_deletion IS 'Felhasználó törlésének időpontja.';

COMMENT ON COLUMN imetyou.sys_user."version" IS '-JPA optimistic locking-\n#{"fieldLabel":"-JPA optimistic locking-", "fieldI18n": "field.common.version", "groupId": "", "showOnForm" : "false"}#';

COMMENT ON COLUMN imetyou.sys_user.user_email IS 'Felhasználó e-mail címe. Kötelező és egyedinek kell lennie domain-en belül.';

COMMENT ON COLUMN imetyou.sys_user.user_password IS 'Felhasználó jelszava. A jelszó titkosításához sha256Hex algoritmust használunk.';

COMMENT ON COLUMN imetyou.sys_user.user_role IS 'Felhasználó szerepe.\nLehetséges értékei :';

COMMENT ON COLUMN imetyou.sys_user.user_title IS 'Felhasználó megszólítása, titulusa.';

COMMENT ON COLUMN imetyou.sys_user.user_name IS 'Felhasználói név. nem kötelező mező magánszemély esetén.';

COMMENT ON COLUMN imetyou.sys_user.user_family_name IS 'Felhasználó családi neve';

COMMENT ON COLUMN imetyou.sys_user.user_given_name IS 'Felhasználó keresztneve.';

COMMENT ON COLUMN imetyou.sys_user.user_gender_mdci IS 'Felhasználó neme.\nÉrtéke a md_collection tábla azonosítója.\nmd_group (GENDER)';

COMMENT ON COLUMN imetyou.sys_user.user_nickname IS 'Felhasználó alias neve.';

COMMENT ON COLUMN imetyou.sys_user.user_phone IS 'Felhasználó telefonszáma.';

COMMENT ON COLUMN imetyou.sys_user.user_location_lat IS 'Felhasználó lokációja: LAT';

COMMENT ON COLUMN imetyou.sys_user.user_location_lon IS 'Felhasználó lokációja. LON';

CREATE TABLE imetyou.sys_user_relations_sw ( 
	id                   text  NOT NULL ,
	created              timestamp DEFAULT now() NOT NULL ,
	creator              text  NOT NULL ,
	modified             timestamp DEFAULT now() NOT NULL ,
	modifier             text  NOT NULL ,
	deleted_10           integer DEFAULT 0  ,
	"version"            integer DEFAULT 1  ,
	from_login           varchar(255)  NOT NULL ,
	to_login             varchar(255)  NOT NULL ,
	CONSTRAINT pk__template_id_10 PRIMARY KEY ( id )
 );

COMMENT ON TABLE imetyou.sys_user_relations_sw IS 'Felhasználók közötti kapcsolatokat ír le.\nAgency-hez tartozó advertiser-eket vagy Club-okhoz tartozó advertiser -eket.';

COMMENT ON COLUMN imetyou.sys_user_relations_sw.id IS 'Azonosító';

COMMENT ON COLUMN imetyou.sys_user_relations_sw.created IS '-Létrehozás dátuma-';

COMMENT ON COLUMN imetyou.sys_user_relations_sw.creator IS 'Rekord létrehozójának felhasználóneve';

COMMENT ON COLUMN imetyou.sys_user_relations_sw.modified IS 'Rekord utolsó módosításának időpontja';

COMMENT ON COLUMN imetyou.sys_user_relations_sw.modifier IS 'Rekord módosítójának felhasználóneve';

COMMENT ON COLUMN imetyou.sys_user_relations_sw.deleted_10 IS 'Rekord logikai törölt-e';

COMMENT ON COLUMN imetyou.sys_user_relations_sw."version" IS '-JPA optimistic locking-';

CREATE TABLE imetyou.sys_user_role_sw ( 
	id                   text  NOT NULL ,
	created              timestamp DEFAULT now() NOT NULL ,
	creator              text  NOT NULL ,
	modified             timestamp DEFAULT now() NOT NULL ,
	modifier             text  NOT NULL ,
	deleted_10           integer DEFAULT 0  ,
	"version"            integer DEFAULT 1  ,
	user_id              text  NOT NULL ,
	role_id              text  NOT NULL ,
	CONSTRAINT pk__template_id_9 PRIMARY KEY ( id )
 );

COMMENT ON TABLE imetyou.sys_user_role_sw IS 'Kapcsolótábla a felhasználók és a szerepek között n-n kapcsolat';

COMMENT ON COLUMN imetyou.sys_user_role_sw.id IS 'Azonosító';

COMMENT ON COLUMN imetyou.sys_user_role_sw.created IS '-Létrehozás dátuma-';

COMMENT ON COLUMN imetyou.sys_user_role_sw.creator IS 'Rekord létrehozójának felhasználóneve';

COMMENT ON COLUMN imetyou.sys_user_role_sw.modified IS 'Rekord utolsó módosításának időpontja';

COMMENT ON COLUMN imetyou.sys_user_role_sw.modifier IS 'Rekord módosítójának felhasználóneve';

COMMENT ON COLUMN imetyou.sys_user_role_sw.deleted_10 IS 'Rekord logikai törölt-e';

COMMENT ON COLUMN imetyou.sys_user_role_sw."version" IS '-JPA optimistic locking-';

COMMENT ON COLUMN imetyou.sys_user_role_sw.user_id IS 'felhasználói azonosító';

COMMENT ON COLUMN imetyou.sys_user_role_sw.role_id IS 'szerep azonosítója';
