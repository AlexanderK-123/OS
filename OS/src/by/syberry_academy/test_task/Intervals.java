package by.syberry_academy.test_task;

public class Intervals {

	public static String intervalConstruction(String[] args) {
		String[] intervalLetter = { "m", "M", "P" };
		String[] notes = { "C", "D", "E", "F", "G", "A", "B" };

		String name = args[0];
		String note = args[1];
		String direction = args[2];

		String iLetter = name.substring(0, 1);
		int iNum = Integer.parseInt(name.substring(1));
		
		String noteLetter = note.substring(0, 1);
		String noteSign = note.substring(1);
		
		int bufNum = iNum;
		int index = 0;
		int qSemitons = 0;
		String result = null;

		// System.out.println(iLetter);

		for (int i = 0; i < notes.length; i++) {
			if (noteLetter == notes[i]) {
				index = i;
			}
		}

		int j = index;

		// asc

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
		
		if(noteSign == "#") {
			qSemitons--;
		}else if(noteSign == "b"){
			qSemitons++;
		}

		// dsc

//		while (bufNum > 1) {
//			if (j == 0) {
//				j = notes.length - 1;
//				result = notes[j];
//				qSemitons++;
//				bufNum--;
//			} else if (j == 3) {
//				result = notes[--j];
//				qSemitons++;
//				bufNum--;
//			} else {
//				result = notes[--j];
//				qSemitons += 2;
//				bufNum--;
//			}
//		}

		System.out.println(result + " " + qSemitons);

		// countSemitons

		return null;

	}

	public static String intervalIdentification(String[] args) {
		return null;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] arr = { "m2", "A", "asc" };

		intervalConstruction(arr);
	}
}
