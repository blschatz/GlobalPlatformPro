package pro.javacard.gp.tests;

import javax.smartcardio.Card;
import javax.smartcardio.CardTerminal;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import pro.javacard.gp.GlobalPlatform;
import apdu4j.LoggingCardTerminal;
import apdu4j.TerminalManager;

public abstract class TestRealCard {

	static GlobalPlatform gp;
	static Card card;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		CardTerminal terminal = TerminalManager.getTheReader();
		terminal = LoggingCardTerminal.getInstance(terminal);
		card = terminal.connect("*");
		gp = new GlobalPlatform(card.getBasicChannel());
		gp.beVerboseTo(System.out);
		gp.select(null);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		TerminalManager.disconnect(card, true);
	}

}
