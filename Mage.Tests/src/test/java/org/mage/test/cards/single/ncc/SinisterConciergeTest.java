package org.mage.test.cards.single.ncc;

import mage.constants.PhaseStep;
import mage.constants.Zone;
import mage.counters.CounterType;
import org.junit.Test;
import org.mage.test.serverside.base.CardTestPlayerBase;

/**
 * {@link mage.cards.s.SinisterConcierge Sinister Concierge}
 * 2/1
 * When Sinister Concierge dies, you may exile it and put three time counters on it.
 * If you do, exile up to one target creature and put three time counters on it.
 * Each card exiled this way that doesn’t have suspend gains suspend.
 * @author Alex-Vasile
 */
public class SinisterConciergeTest extends CardTestPlayerBase {

    private static final String sinisterConcierge = "Sinister Concierge"; // 2/1
    private static final String bondedConstruct = "Bonded Construct";  // Simple 2/1
    private static final String lightningBolt = "Lightning Bolt"; // {R}


    /**
     * Test that both cards are exiled properly.
     */
    @Test
    public void testWorking() {
        // TODO: remove multiple calls to execute()
        addCard(Zone.HAND, playerA, lightningBolt);
        addCard(Zone.BATTLEFIELD, playerA, sinisterConcierge);
        addCard(Zone.BATTLEFIELD, playerA, "Mountain");

        addCard(Zone.BATTLEFIELD, playerB, bondedConstruct);

        setStrictChooseMode(true);

        castSpell(1, PhaseStep.PRECOMBAT_MAIN, playerA, lightningBolt, sinisterConcierge);
        setChoice(playerA, true);
        addTarget(playerA, bondedConstruct);

        setStopAt(1, PhaseStep.END_COMBAT);
        execute();

        assertExileCount(playerA, sinisterConcierge, 1);
        assertCounterOnExiledCardCount(sinisterConcierge, CounterType.TIME, 3);

        assertExileCount(playerB, bondedConstruct, 1);
        assertCounterOnExiledCardCount(bondedConstruct, CounterType.TIME, 3);

        setStopAt(5, PhaseStep.PRECOMBAT_MAIN);
        execute();

        assertExileCount(playerA, sinisterConcierge, 1);
        assertCounterOnExiledCardCount(sinisterConcierge, CounterType.TIME, 1);

        assertExileCount(playerB, bondedConstruct, 1);
        assertCounterOnExiledCardCount(bondedConstruct, CounterType.TIME, 1);

        setChoice(playerB, true); // yes to cast
        setStopAt(6, PhaseStep.PRECOMBAT_MAIN);
        execute();
        assertExileCount(playerA, sinisterConcierge, 1);
        assertExileCount(playerB, bondedConstruct, 0);
        assertPermanentCount(playerB, bondedConstruct, 1);

        setChoice(playerA, true); // yes to cast
        setStopAt(7, PhaseStep.PRECOMBAT_MAIN);
        execute();
        assertExileCount(playerA, sinisterConcierge, 0);
        assertPermanentCount(playerA, sinisterConcierge, 1);
        assertExileCount(playerB, bondedConstruct, 0);
        assertPermanentCount(playerB, bondedConstruct, 1);
    }
}
