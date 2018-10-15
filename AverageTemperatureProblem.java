package us.vineetha;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class AverageTemperatureProblem {

	public static void main(String[] args) {
		
		Scanner scannerObject = new Scanner(System.in);
		
		System.out.println("Enter the average time duration value for already given input");

		int averageTime = scannerObject.nextInt();
		String[] inputArray = { "1,10000,40", "1,10002,45", "1,11015,50", "2,10005,42", "2,11051,45", "2,12064,42",
		"2,13161,42" };

		Map<Long, Integer> buildMapStorage = new TreeMap<>();

		for (int i = 0; i < inputArray.length; i++) {
			String[] line = inputArray[i].split(",");
			buildMapStorage.put(Long.parseLong(line[1]), Integer.parseInt(line[2]));
		}
		long startElement = 0;
		long endElement = 0;
		boolean flag = true;
		double total = 0;
		int count = 0;
		Map<String, Double> result = new LinkedHashMap<>();
		List<Long> l = new ArrayList<>(buildMapStorage.keySet());
		for (int i = 0; i < l.size(); i++) {
			if (flag) {
				startElement = l.get(i);
				endElement = startElement + (averageTime * 1000) - 1;
				flag = false;
			}
			if (l.get(i) <= endElement) {
				total += buildMapStorage.get(l.get(i));
				count++;
			} else {
				i--;
				result.put(startElement + " -- " + endElement, total / count);
				startElement = endElement + 1;
				endElement = startElement + (averageTime * 1000) - 1;
				total = 0;
				count = 0;
			}
		}
		if (total != 0 || count != 0) {
			result.put(startElement + " -- " + endElement, total / count);
		}
		for (String s : result.keySet()) {
			System.out.println(s + "\t" + result.get(s));
		}
	}
}