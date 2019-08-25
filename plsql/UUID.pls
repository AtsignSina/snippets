CREATE 
OR REPLACE FUNCTION RANDOM_UUD_RAW RETURN RAW IS v_uuid RAW(16);
BEGIN v_uuid := sys.dbms_crypto.Randombytes(16);
v_uuid := utl_raw.Overlay(
  utl_raw.Bit_or(
    utl_raw.Bit_and(
      utl_raw.Substr(v_uuid, 7, 1), 
      '0F'
    ), 
    '40'
  ), 
  v_uuid, 
  7, 
  1
);
v_uuid := utl_raw.Overlay(
  utl_raw.Bit_or(
    utl_raw.Bit_and(
      utl_raw.Substr(v_uuid, 9, 1), 
      '3F'
    ), 
    '80'
  ), 
  v_uuid, 
  9, 
  1
);
RETURN v_uuid;
END random_uud_raw;
--
--
CREATE 
OR REPLACE FUNCTION UUID_FORMATTER_CONCAT(v_uuid RAW) RETURN VARCHAR2 IS v_str VARCHAR2(36);
BEGIN v_str := lower(
  SUBSTR(v_uuid, 1, 8) || '-' || SUBSTR(v_uuid, 9, 4) || '-' || SUBSTR(v_uuid, 13, 4) || '-' || SUBSTR(v_uuid, 17, 4) || '-' || SUBSTR(v_uuid, 21)
);
RETURN v_str;
END UUID_FORMATTER_CONCAT;
--
CREATE 
OR REPLACE FUNCTION UUID_FORMATTER_REGEX(v_uuid RAW) RETURN VARCHAR2 IS v_str VARCHAR2(36);
BEGIN v_str := lower(
  regexp_replace(
    v_uuid, '(.{8})(.{4})(.{4})(.{4})(.{12})', 
    '\1-\2-\3-\4-\5'
  )
);
RETURN v_str;
END UUID_FORMATTER_REGEX;
--
CREATE 
OR REPLACE FUNCTION RANDOM_UUID_STR RETURN VARCHAR2 AS BEGIN RETURN UUID_FORMATTER_CONCAT(
  RANDOM_UUD_RAW()
);
END RANDOM_UUID_STR;
--
CREATE 
OR REPLACE FUNCTION RANDOM_UUID_STR_REGEX RETURN VARCHAR2 AS BEGIN RETURN UUID_FORMATTER_REGEX(
  RANDOM_UUD_RAW()
);
END RANDOM_UUID_STR_REGEX;
