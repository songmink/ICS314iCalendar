
public class greatDistance {

	/*
	 * I used the first formula of wikipedia, haversin, but the distance is a bit large...
	 */
	public static double cal(double lat1, double lon1, double lat2, double lon2) {
		// same position
		if(lat1 == lat2 && lon1 == lon2) {
			return 0;
		}
		/* Earth Mean Radius */
		//final double earthRmile = 3953.0;
		final double earthRKm = 6371;
		double lat1R = Math.toRadians(lat1);
		double lat2R = Math.toRadians(lat2);
		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(lon2 - lon1);
		double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(lat1R) * Math.cos(lat2R) * Math.sin(dLon/2) * Math.sin(dLon/2);
		double c = 2 * Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
		double dist = earthRKm * c;
		return dist;
	}
	
}
