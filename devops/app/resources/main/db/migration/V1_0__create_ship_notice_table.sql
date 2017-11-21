CREATE TABLE ship_notice_req
(
  vin INTEGER NOT NULL,
  customer character varying(50) NOT NULL,
  message_origin_time character varying(100)
)
WITH (
OIDS=FALSE
);

