package com.szqb.util;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.MetaTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.szqb.bean.Content;
import com.szqb.bean.Page;
import com.szqb.bean.Title;
import com.szqb.web.IndexBean;

public class QbExp {
	static String url = "http://www.sz-qb.com/";
	static String mi = "html/2011-03/28/";
	static String end = "node_2.htm";

	public void run() {
		expIndex();
		new Thread() {
			public void run() {
				try {
					while (true) {
						Thread.sleep(1800000);
						expIndex();						
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
	}
	private String getNowUrl() {
		String html = getHtml(url);
		Parser parser;
		try {
			parser = new Parser(html);
			NodeFilter[] nodeFilter = new NodeFilter[2];
			NodeFilter filterLink = new NodeClassFilter(MetaTag.class);
			NodeFilter style = new HasAttributeFilter("HTTP-EQUIV", "REFRESH");
			nodeFilter[0]=filterLink;
			nodeFilter[1]=style;
			AndFilter andFilter = new AndFilter(nodeFilter);
			NodeList nodes = parser.extractAllNodesThatMatch(andFilter);
			if(nodes.size()>0){
				Tag me=(Tag) nodes.elementAt(0);				
                String url = me.toHtml();
                String[] meta=url.split("URL=");
                String [] u=meta[1].split("node_2.htm");
				return u[0];
			}
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void expIndex() {
		List<Title> list = new ArrayList<Title>();
		Map<String, Page> pageList = new TreeMap<String, Page>();
		Map<String, Content> contentList = new TreeMap<String, Content>();
		IndexBean.setContentList(contentList);
		String middle=getNowUrl();
		System.out.println(mi);
		if(middle.equals(mi)){
			return;
		}else{
			mi=middle;
		}
		try {
			String html = getHtml(url + middle + end);
			Parser parser = new Parser(html);
			parser.setEncoding("utf-8");
			NodeFilter linkFilter = QbFitler.indexFilter();
			NodeList nodes = parser.extractAllNodesThatMatch(linkFilter);
			if (nodes != null) {
				for (int j = 0; j < nodes.size(); j++) {
					long haltHour = Calendar.getInstance().getTime().getTime();
					Page page = new Page();
					IndexBean.setPageList(pageList);
					pageList.put("" + haltHour, page);
					Title title = new Title();
					Parser parser2 = new Parser(nodes.elementAt(j).toHtml());
					NodeFilter pageFilter = QbFitler.pageFilter();
					NodeList nodes2 = parser2
							.extractAllNodesThatMatch(pageFilter);
					String linkNames = ((Tag) nodes2.elementAt(0))
							.getChildren().toHtml();
					title.setTitle(linkNames);

					title.setId(haltHour + "");
					list.add(title);
					IndexBean.setTitleList(list);
					System.out.println(list);
					page.setId(haltHour + "");
					page.setTitle(linkNames);

					Parser parser3 = new Parser(nodes.elementAt(j).toHtml());
					NodeFilter filterLink = new NodeClassFilter(LinkTag.class);
					NodeList nodesLink = parser3
							.extractAllNodesThatMatch(filterLink);
					List<Content> contentlist = new ArrayList<Content>();
					page.setList(contentlist);
					for (int i = 0; i < nodesLink.size(); i++) {
						String urlLink = "";
						System.out.println(((Tag) nodesLink.elementAt(i)));
						urlLink = ((LinkTag) nodesLink.elementAt(i))
								.extractLink(); // 取出连接的URL地址

						Content content = new Content();
						content.setId(haltHour + "" + i);
						contentlist.add(content);
						String contenthtml = getHtml(url + middle + urlLink);
						Parser parser4 = new Parser(contenthtml);
						parser4.setEncoding("utf-8");
						NodeFilter secondfilter = QbFitler.secondFilter();
						NodeList aLink = parser4
								.extractAllNodesThatMatch(secondfilter);
						if (aLink != null) {
							for (int k = 0; k < aLink.size(); k++) {
								content.setSecondtitle(((Tag) aLink
										.elementAt(0)).getChildren().toHtml());
							}
						}

						String[] content1 = contenthtml
								.split("<founder-content>");
						String[] contetn2 = content1[1]
								.split("</founder-content>");
						content.setContent(contetn2[0]);
						String[] imagetd = contenthtml
								.split("<table width=\"315\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\">");
						if (imagetd.length > 1) {
							String[] imagetd2 = imagetd[1]
									.split("<founder-content>");
							System.out.println(imagetd2[0]);
							String imagehtml = imagetd2[0];
							Parser parserimage = new Parser(imagehtml);
							NodeFilter filteriamge = new NodeClassFilter(
									ImageTag.class);
							NodeList fileLink = parserimage
									.extractAllNodesThatMatch(filteriamge);
							ArrayList<byte[]> imageList = new ArrayList<byte[]>();
							content.setImageList(imageList);
							for (int k = 0; k < fileLink.size(); k++) {
								String imageurl = ((ImageTag) fileLink
										.elementAt(k)).getImageURL();
								System.out.println(imageurl);
								String[] abc = imageurl.split("../../../");
								byte[] image = dealImg(url + abc[1]);
								imageList.add(image);

							}
						}

						contentList.put(haltHour + "" + i, content);
					}
					Parser parserpagetitle = new Parser(nodes.elementAt(j)
							.toHtml());
					NodeFilter pagetitlefilter = QbFitler.pageTitleFilter();
					NodeList pagetitle = parserpagetitle
							.extractAllNodesThatMatch(pagetitlefilter);
					for (int i = 0; i < pagetitle.size(); i++) {
						Content content = contentlist.get(i);
						content.setTitle(pagetitle.elementAt(i).getFirstChild()
								.toHtml());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getHtml(String path) {
		String html = "";
		Parser parser;
		try {
			parser = new Parser((HttpURLConnection) (new URL(path))
					.openConnection());
			parser.setEncoding("utf-8");
			NodeFilter linkFilter = new NodeClassFilter(Tag.class);
			NodeList nodes = parser.extractAllNodesThatMatch(linkFilter);
			html = nodes.elementAt(0).toHtml();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return html;
	}

	private byte[] dealImg(String imgUrl) {
		ImageDeal id = new ImageDeal();
		InputStream is = id.downloadFile(imgUrl);
		try {
			NewScaleImage scImage = new NewScaleImage(is);
			int width=scImage.getWidth();
			int hight=scImage.getHeight();
			double r=1.0*255/width;
			int h=(int) (hight*r);
			BufferedImage phoneImage = scImage.scaleImage(255, h);
			// BufferedImage bimage1 = scImage.getBufferedImage(is2);
			byte[] phoneb = ImageDeal.imageToBytes(phoneImage, "jpg");

			return phoneb;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
