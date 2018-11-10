package at.suizi.cv.trellomailparser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class LatestYoutubeLink {

	private final String BASE_URL = "https://www.googleapis.com/youtube/v3/activities";
	private final String YOUTUBE_BASE_URL = "https://www.youtube.com/watch?v=";

	@Autowired
	private RestTemplate getYoutube;

	@RequestMapping(path = "/latest-youtube-link", method = RequestMethod.GET)
	public String getLatestYoutubeLink() {
		MultiValueMap<String, String> params = new HttpHeaders();
		params.add("key", "AIzaSyD9YEWbEpoE1O9_kKmtEtxFB6736qM6idQ");
		params.add("part", "contentDetails");
		params.add("maxResults", "1");
		params.add("channelId", "UCZCFH_cFdf6OtpXklKZf-vw");
		params.add("fields", "items/contentDetails/upload");

		YoutubeLinkResponse result = null;
		try {
			result = getYoutube.getForObject(
					UriComponentsBuilder.fromUriString(BASE_URL).queryParams(params).toUriString(),
					YoutubeLinkResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		return result == null ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR).toString()
				: convertToYoutubeLink(result);
	}

	private String convertToYoutubeLink(YoutubeLinkResponse result) {
		return YOUTUBE_BASE_URL + result.getVideoId();
	}
}
