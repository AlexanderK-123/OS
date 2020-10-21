package by.syberry_academy.test_task;

import java.util.ArrayList;
import java.util.List;

public class Intervals {

	static String[] intervals = { null, "m2", "M2", "m3", "M3", "P4", null, "P5", "m6", "M6", "m7", "M7", "P8" };
	static String[] notes = { "C", "D", "E", "F", "G", "A", "B" };
	static String name = null, note1 = null, note2 = null, direction = null;

	public static String intervalConstruction(String[] args) throws Exception {
		switch (args.length) {
		case 3:
			name = args[0];
			note1 = args[1];
			direction = args[2];
			break;
		case 2:
			name = args[0];
			note1 = args[1];
			direction = "asc";
			break;
		default:
			throw new Exception("Illegal number of elements in input array!");
		}

		int iNum = Integer.parseInt(name.substring(1));

		String noteLetter = note1.substring(0, 1);
		String noteSign = note1.substring(1);

		int bufNum = iNum;
		int index = 0;
		int qSemitons = 0;
		String result = null;

		for (int i = 0; i < notes.length; i++) {
			if (noteLetter.equals(notes[i])) {
				index = i;
				break;
			}
		}

		int j = index;

		switch (direction.toLowerCase()) {
		case "asc":
			while (bufNum > 1) {
				if (j == notes.length - 1) {
					j = 0;
					result = notes[j];
					qSemitons++;
					bufNum--;
				} else if (j == 2) {
					result = notes[++j];
					qSemitons++;
					bufNum--;
				} else {
					result = notes[++j];
					qSemitons += 2;
					bufNum--;
				}
			}
			if (noteSign.equals("#")) {
				qSemitons--;
			} else if (noteSign.equals("b")) {
				qSemitons++;
			}
			for (int i = 1; i < intervals.length; i++) {
				if (name.equals(intervals[i])) {
					switch (qSemitons - i) {
					case 1:
						return result + "b";
					case 2:
						return result + "bb";
					case -1:
						return result + "#";
					case -2:
						return result + "##";
					default:
						return result;
					}
				}
			}

		case "dsc":
			while (bufNum > 1) {
				if (j == 0) {
					j = notes.length - 1;
					result = notes[j];
					qSemitons++;
					bufNum--;
				} else if (j == 3) {
					result = notes[--j];
					qSemitons++;
					bufNum--;
				} else {
					result = notes[--j];
					qSemitons += 2;
					bufNum--;
				}
			}
			if (noteSign.equals("#")) {
				qSemitons++;
			} else if (noteSign.equals("b")) {
				qSemitons--;
			}
			for (int i = 1; i < intervals.length; i++) {
				if (name.equals(intervals[i])) {
					switch (qSemitons - i) {
					case 1:
						return result + "#";
					case 2:
						return result + "##";
					case -1:
						return result + "b";
					case -2:
						return result + "bb";
					default:
						return result;
					}
				}
			}
		default:
			throw new Exception("Incorrect value of the third parameter in the array!");
		}
	}

	public static String intervalIdentification(String[] args) throws Exception {
		switch (args.length) {
		case 3:
			note1 = args[0];
			note2 = args[1];
			direction = args[2];
			break;
		case 2:
			note1 = args[0];
			note2 = args[1];
			direction = "asc";
			break;
		default:
			throw new Exception("Illegal number of elements in input array!");
		}

		String noteLetter1 = note1.substring(0, 1);
		String noteSign1 = note1.substring(1);

		String noteLetter2 = note2.substring(0, 1);
		String noteSign2 = note2.substring(1);

		int qSteps = 1;
		int index = 0;
		int qSemitons = 0;

		for (int i = 0; i < notes.length; i++) {
			if (noteLetter1.equals(notes[i])) {
				index = i;
				break;
			}
		}

		int j = index;

		switch (direction.toLowerCase()) {
		case "asc":
			while (!noteLetter2.equals(notes[j])) {
				j++;
				qSteps++;
				if (j == notes.length) {
					j = 0;
					qSemitons++;
				} else if (j == 3) {
					qSemitons++;
				} else {
					qSemitons += 2;
				}
			}
			if (noteSign1.equals("#")) {
				qSemitons--;
			} else if (noteSign1.equals("##")) {
				qSemitons -= 2;
			} else if (noteSign1.equals("b")) {
				qSemitons++;
			} else if (noteSign1.equals("bb")) {
				qSemitons += 2;
			}
			if (noteSign2.equals("#")) {
				qSemitons++;
			} else if (noteSign2.equals("##")) {
				qSemitons += 2;
			} else if (noteSign2.equals("b")) {
				qSemitons--;
			} else if (noteSign2.equals("bb")) {
				qSemitons -= 2;
			}

			for (int i = 1; i < intervals.length; i++) {
				if (qSemitons == i) {
					if (intervals[i] == null || qSteps != Integer.parseInt(intervals[i].substring(1))) {
						throw new Exception("Cannot identify the interval");
					} else {
						return intervals[i];
					}
				}
			}

		case "dsc":
			while (!noteLetter2.equals(notes[j])) {
				j--;
				qSteps++;
				if (j == -1) {
					j = notes.length - 1;
					qSemitons++;
				} else if (j == 2) {
					qSemitons++;
				} else {
					qSemitons += 2;
				}
			}
			if (noteSign1.equals("#")) {
				qSemitons++;
			} else if (noteSign1.equals("##")) {
				qSemitons += 2;
			} else if (noteSign1.equals("b")) {
				qSemitons--;
			} else if (noteSign1.equals("bb")) {
				qSemitons -= 2;
			}
			if (noteSign2.equals("#")) {
				qSemitons--;
			} else if (noteSign2.equals("##")) {
				qSemitons -= 2;
			} else if (noteSign2.equals("b")) {
				qSemitons++;
			} else if (noteSign2.equals("bb")) {
				qSemitons += 2;
			}

			for (int i = 0; i < intervals.length; i++) {
				if (qSemitons == i) {

					if (intervals[i] == null || qSteps != Integer.parseInt(intervals[i].substring(1))) {
						throw new Exception("Cannot identify the interval");
					} else {
						return intervals[i];
					}
				}
			}
		default:
			throw new Exception("Incorrect value of the third parameter in the array!");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] arr = { "m2", "C", "asc" };
		String[] arr1 = { "M2", "C#", "dsc" };
		String[] arr2 = { "m3", "Eb" };
		String[] arr3 = { "M3", "Cb", "dsc" };
		String[] arr4 = { "P4", "E#" };
		String[] arr5 = { "P5", "Db", "dsc" };
		String[] arr6 = { "M3", "B#", "asc" };
		String[] arr7 = { "M6", "A", "dsc" };
		String[] arr8 = { "m7", "Db", "asc", "ASD" };
		String[] arr9 = { "m3", "E#", "ñsc" };
		String[] arr0 = { "P8", "Gb", "asc" };

		List<String[]> list = new ArrayList<>();

		list.add(arr);
		list.add(arr1);
		list.add(arr2);
		list.add(arr3);
		list.add(arr4);
		list.add(arr5);
		list.add(arr6);
		list.add(arr7);
		list.add(arr8);
		list.add(arr9);
		list.add(arr0);

		System.out.println("intervalConstruction\n");
		
		for (String[] e : list) {
			try {
				System.out.println(intervalConstruction(e));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		

		String[] ar = { "C", "D" };
		String[] ar1 = { "B", "F#", "asc" };
		String[] ar2 = { "Fb", "Gbb" };
		String[] ar3 = { "G", "F#", "asc" };
		String[] ar4 = { "Bb", "A", "dsc" };
		String[] ar5 = { "Cb", "G", "dsc" };
		String[] ar6 = { "G#", "D", "dsc" };
		String[] ar7 = { "E", "B", "dsc" };
		String[] ar8 = { "E#", "D#", "dsc" };
		String[] ar9 = { "D", "E#", "ñsc" };
		String[] ar0 = { "B", "G#", "dsc" };

		List<String[]> list1 = new ArrayList<>();

		list1.add(ar);
		list1.add(ar1);
		list1.add(ar2);
		list1.add(ar3);
		list1.add(ar4);
		list1.add(ar5);
		list1.add(ar6);
		list1.add(ar7);
		list1.add(ar8);
		list1.add(ar9);
		list1.add(ar0);

		System.out.println("\nintervalIdentification\n");
		
		for (String[] e : list1) {
			try {
				System.out.println(intervalIdentification(e));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
}
