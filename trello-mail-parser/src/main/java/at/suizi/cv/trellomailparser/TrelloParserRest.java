package at.suizi.cv.trellomailparser;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class TrelloParserRest {
	@RequestMapping(path="/parse", method=RequestMethod.POST)
	public String format(@RequestBody String msg) {
		return convertBodyToTrelloMessage(msg);
	}

	private String convertBodyToTrelloMessage(String msg) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < msg.length(); i++) {
			char c = msg.charAt(i);
			if(c == '[') {
				
				if(i+1 < msg.length()) {
					if(msg.charAt(i+1) == 'h') {
						if(isImage(msg, i+1)) {
							sb.append("![Web-Image](");
						} else {
							sb.append("[Link](");
						}
						continue;
					}
				}
			} else if(c == ']'){
				sb.append(")");
				continue;
			}
			sb.append(c);
		}
		return sb.toString();
	}

	private boolean isImage(String msg, int index) {
		String link = "";
		for(int i = index ; i < msg.length(); i++) {
			char c =msg.charAt(i);
			if(c == ']') {
				return false;
			}
			link += c;
			if(link.endsWith(".jpg") || link.endsWith(".png")) {
				return true;
			}
		}
		return false;
	}
}
