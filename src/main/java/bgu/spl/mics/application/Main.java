package bgu.spl.mics.application;

import bgu.spl.mics.application.passiveObjects.InitializeBattle;



/** This is the Main class of the application. You should parse the input file,
 * create the different components of the application, and run the system.
 * In the end, you should output a JSON.
 */
public class Main {
	public static void main(String[] args) {
//		if (args.length != 0) {

			InitializeBattle m = new InitializeBattle();
			m.run("input.json");
			m.outputFile("Output.json");
//		}
	}
}