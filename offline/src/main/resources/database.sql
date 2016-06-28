DROP TABLE IF EXISTS PositionInfo;

CREATE TABLE PositionInfo (
	region 	 VARCHAR(50),
	location VARCHAR(50),
	latitude DOUBLE,
	longitude DOUBLE,

	PRIMARY KEY(region, location)
);

