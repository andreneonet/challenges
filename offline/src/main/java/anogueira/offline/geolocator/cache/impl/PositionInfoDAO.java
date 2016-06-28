package anogueira.offline.geolocator.cache.impl;

import anogueira.offline.geolocator.cache.PositionInfo;

/**
 * The DAO interface of the PositionInfo to implement persistence layer 
 *
 * @author Andre Nogueira
 */
interface PositionInfoDAO {
	PositionInfo find(String region, String place);
	void insert(PositionInfo position);
}
