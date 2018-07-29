package com.bagnesapps.utils;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class FirestoreCsvImporter {

	public static void main(String[] args) throws Exception {
		try(Scanner sc = new Scanner(new File("D:/IMA/docs/initial_products.csv"));
				FileWriter fw = new FileWriter("D:/IMA/docs/initial_products.json");){
			fw.write("{");
			Pattern pattern = Pattern.compile("[0-9]{1,}x[0-9]{1,}");
			while(sc.hasNextLine()) {
				String json = "\"%s\": { "
						+ "\"id\": \"%s\","
						+ "\"taxCode\": \"%s\","
						+ "\"name\": \"%s\","
						+ "\"price\": \"%s\","
						+ "\"code\": \"%s\","
						+ "\"category\": \"%s\","
						+ "\"status\": \"%s\","
						+ "\"piecesInBox\": \"%s\","
						+ "\"followUserProfitPercentage\": \"%s\""
						+ " },";
				String[] line = sc.nextLine().split(";");
				if(line.length == 5) {
					String category = StringUtils.startsWithIgnoreCase(line[2], "Frozen") ? "FROZEN" : "DRY";
					String status = "Active";
					String followUserProfitPercentage = "true";
					String piecesInBox ="1";
					Matcher matcher = pattern.matcher(line[2]);
					if(matcher.find()) {
						String quantity = matcher.group(0);
						piecesInBox = quantity.toLowerCase().split("x")[0];
					}
					String product = String.format(json, line[0], line[0], line[1], line[2], StringUtils.replaceAll(line[3], "\\$", "") , line[4], category, status, piecesInBox, followUserProfitPercentage); 
					fw.write(product);
				}				
			}
			fw.write("}");
		}finally {
			System.out.println("Job done");
		}
	}

}
