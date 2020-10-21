package by.syberry_academy.test_task;

public class Intervals {

	public static String intervalConstruction(String[] args) {
		String[] intervals = { null, "m2", "M2", "m3", "M3", "P4", null, "P5", "m6", "M6", "m7", "M7", "P8" };
		String[] notes = { "C", "D", "E", "F", "G", "A", "B" };
		String name = null, note = null, direction = null;

		switch (args.length) {
		case 3:
			name = args[0];
			note = args[1];
			direction = args[2];
			break;
		case 2:
			name = args[0];
			note = args[1];
			direction = "asc";
			break;
		default:
			// exception
			return null;
		}
		
		int iNum = Integer.parseInt(name.substring(1));

		String noteLetter = note.substring(0, 1);
		String noteSign = note.substring(1);

		int bufNum = iNum;
		int index = 0;
		int qSemitons = 0;
		String result = null;

		for (int i = 0; i < notes.length; i++) {
			if (noteLetter.equalsIgnoreCase(notes[i])) {
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
			if (noteSign.equalsIgnoreCase("#")) {
				qSemitons--;
			} else if (noteSign.equalsIgnoreCase("b")) {
				qSemitons++;
			}
			for (int i = 1; i < intervals.length; i++) {
				if (name.equalsIgnoreCase(intervals[i])) {
					switch (qSemitons - i) {
					case 1:
						return result + "b";
					case 2:
						return result + "bb";
					case -1:
						return result + "#";
					case -2:
						return result + "##";
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
			if (noteSign.equalsIgnoreCase("#")) {
				qSemitons++;
			} else if (noteSign.equalsIgnoreCase("b")) {
				qSemitons--;
			}
			for (int i = 1; i < intervals.length; i++) {
				if (name.equalsIgnoreCase(intervals[i])) {
					switch (qSemitons - i) {
					case 1:
						return result + "#";
					case 2:
						return result + "##";
					case -1:
						return result + "b";
					case -2:
						return result + "bb";
					}
				}
			}
		default:
			// exception
			return null;
		}
	}

	public static String intervalIdentification(String[] args) {
		return null;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] arr = { "m3", "C", "dsc" };

		System.out.println(intervalConstruction(arr));
	}
}
