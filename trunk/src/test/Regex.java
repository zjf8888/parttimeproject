package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
 public static void main(String args[]){
  String str="<title>title</title>";
  String title;
  Pattern pattern = Pattern.compile("<title(.*?)>(.*)</title>",Pattern.MULTILINE | Pattern.DOTALL);
  Matcher matcher=pattern.matcher(str);
  while(matcher.find()){
   title=matcher.group(2);
   System.out.println(title);
  }
 }
} 
