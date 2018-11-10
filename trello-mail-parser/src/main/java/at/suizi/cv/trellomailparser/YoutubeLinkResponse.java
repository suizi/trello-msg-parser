package at.suizi.cv.trellomailparser;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
class YoutubeLinkResponse {

	private String videoId;

	@JsonProperty("items")
	private void unnestThisShit(JsonNode items) {
		videoId = items.get(0).get("contentDetails").get("upload").get("videoId").asText();
	}

}
