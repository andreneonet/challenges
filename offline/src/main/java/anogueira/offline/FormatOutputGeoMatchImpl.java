package anogueira.offline;


public class FormatOutputGeoMatchImpl implements FormatOutputGeoMatch{
	@Override
	public String format(PhoneNumberContext numberContext) {
		if(numberContext == null){
			return "no match found";
		}else{
			String phoneNumber =  numberContext.getNumber();
			String region =  numberContext.getLookupInfo().getRegion();
			String location = numberContext.getLookupInfo().getLocation();

			if(location == null){
				location = "";
			}
			return String.format("%s ( Region: %s, Location: %s)",phoneNumber,region,location);
		}
	}
}
