package xml.brevaide;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import data.brevaide.Question;

import android.util.Xml;

public class QuestionsXMLHandler {
	private static final String ns = null;
	
	public ArrayList<Question> parse(InputStream in) throws XmlPullParserException, IOException {
	        try {
	            XmlPullParser parser = Xml.newPullParser();
	            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
	            parser.setInput(in, null);
	            parser.nextTag();
	            return readQuestions(parser);
	        } finally {
	            in.close();
	        }
	    }
	
	
	private ArrayList<Question> readQuestions(XmlPullParser parser) throws XmlPullParserException, IOException {
		
		ArrayList<Question> questions = new ArrayList<Question>();
		
	    parser.require(XmlPullParser.START_TAG, ns, "questions");
	    	    
	    while (parser.next() != XmlPullParser.END_TAG) {
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        String name = parser.getName();
	        // Starts by looking for the entry tag
	        if (name.equals("question")) {
	            questions.add(readQuestion(parser));
	        } else {
	            skip(parser);
	        }
	    }  
	    return questions;
	}
	
	
	private Question readQuestion(XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "question");
		
		
	
	    String text = parser.getAttributeValue(null, "text");
	    int answerID = Integer.parseInt(parser.getAttributeValue(null, "answerID"));
	    int reponseCounter = 0;
		String[] answers = new String[3];
		
		while (parser.next() != XmlPullParser.END_TAG) {
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        String name = parser.getName();
	        if (name.equals("reponse") && reponseCounter < 3) {
	            answers[reponseCounter] = readReponse(parser);
	            reponseCounter++;
	        } else {
	            skip(parser);
	        }
	    }
		
		return new Question(text, answers, answerID);
	}
	
	private String readReponse(XmlPullParser parser) throws IOException, XmlPullParserException {
	    parser.require(XmlPullParser.START_TAG, ns, "reponse");
	    
	    String reponse = "";
	    
	    if (parser.next() == XmlPullParser.TEXT) {
	        reponse = parser.getText();
	        parser.nextTag();
	    }
	    parser.require(XmlPullParser.END_TAG, ns, "reponse");
	    
	    return reponse;
	}
	
	private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
	    if (parser.getEventType() != XmlPullParser.START_TAG) {
	        throw new IllegalStateException();
	    }
	    int depth = 1;
	    while (depth != 0) {
	        switch (parser.next()) {
	        case XmlPullParser.END_TAG:
	            depth--;
	            break;
	        case XmlPullParser.START_TAG:
	            depth++;
	            break;
	        }
	    }
	 }

}
