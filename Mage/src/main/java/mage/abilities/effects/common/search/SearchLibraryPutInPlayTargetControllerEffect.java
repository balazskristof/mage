package mage.abilities.effects.common.search;

import mage.abilities.Ability;
import mage.abilities.effects.SearchEffect;
import mage.cards.CardsImpl;
import mage.constants.Outcome;
import mage.constants.Zone;
import mage.filter.StaticFilters;
import mage.game.Game;
import mage.players.Player;
import mage.target.common.TargetCardInLibrary;

/**
 * @author awjackson
 */
public class SearchLibraryPutInPlayTargetControllerEffect extends SearchEffect {

    private final boolean tapped;

    public SearchLibraryPutInPlayTargetControllerEffect(boolean tapped) {
        this(new TargetCardInLibrary(StaticFilters.FILTER_CARD_BASIC_LAND), tapped, Outcome.PutLandInPlay, "its controller");
    }

    public SearchLibraryPutInPlayTargetControllerEffect(TargetCardInLibrary target, boolean tapped, Outcome outcome, String whoSearch) {
        super(target, outcome);
        this.tapped = tapped;
        staticText = whoSearch
                + " may search their library for "
                + target.getDescription()
                + (target.getMaxNumberOfTargets() > 1 ? ", put them onto the battlefield" : ", put it onto the battlefield")
                + (tapped ? " tapped" : "")
                + ", then shuffle";
    }

    protected SearchLibraryPutInPlayTargetControllerEffect(final SearchLibraryPutInPlayTargetControllerEffect effect) {
        super(effect);
        this.tapped = effect.tapped;
    }

    @Override
    public SearchLibraryPutInPlayTargetControllerEffect copy() {
        return new SearchLibraryPutInPlayTargetControllerEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player player = getTargetPointer().getControllerOfFirstTargetOrLKI(game, source);
        if (player == null) {
            return false;
        }
        if (!player.chooseUse(outcome, "Search your library for " + target.getDescription() + '?', source, game)) {
            return true;
        }
        if (player.searchLibrary(target, source, game) && !target.getTargets().isEmpty()) {
            player.moveCards(new CardsImpl(target.getTargets()).getCards(game),
                    Zone.BATTLEFIELD, source, game, tapped, false, false, null);
        }
        player.shuffleLibrary(source, game);
        return true;
    }
}
