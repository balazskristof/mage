package mage.cards.m;

import mage.abilities.Ability;
import mage.abilities.SpellAbility;
import mage.abilities.common.AsEntersBattlefieldAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.effects.common.ChooseModeEffect;
import mage.abilities.effects.common.DrawDiscardControllerEffect;
import mage.abilities.effects.common.continuous.GainAnchorWordAbilitySourceEffect;
import mage.abilities.effects.common.cost.CostModificationEffectImpl;
import mage.abilities.triggers.BeginningOfDrawTriggeredAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.game.stack.Spell;
import mage.players.Player;
import mage.util.CardUtil;

import java.util.Set;
import java.util.UUID;

/**
 * @author emerald000
 */
public final class MonasterySiege extends CardImpl {

    public MonasterySiege(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ENCHANTMENT}, "{2}{U}");

        // As Monastery Siege enters the battlefield, choose Khans or Dragons.
        this.addAbility(new AsEntersBattlefieldAbility(new ChooseModeEffect(ModeChoice.KHANS, ModeChoice.DRAGONS)));

        // * Khans - At the beginning of your draw step, draw an additional card, then discard a card.
        this.addAbility(new SimpleStaticAbility(new GainAnchorWordAbilitySourceEffect(
                new BeginningOfDrawTriggeredAbility(
                        new DrawDiscardControllerEffect(1, 1), false
                ), ModeChoice.KHANS
        )));

        // * Dragons - Spells your opponents cast that target you or a permanent you control cost {2} more to cast.
        this.addAbility(new SimpleStaticAbility(new GainAnchorWordAbilitySourceEffect(
                new MonasterySiegeCostIncreaseEffect(), ModeChoice.DRAGONS
        )));
    }

    private MonasterySiege(final MonasterySiege card) {
        super(card);
    }

    @Override
    public MonasterySiege copy() {
        return new MonasterySiege(this);
    }
}

class MonasterySiegeCostIncreaseEffect extends CostModificationEffectImpl {

    MonasterySiegeCostIncreaseEffect() {
        super(Duration.WhileOnBattlefield, Outcome.Benefit, CostModificationType.INCREASE_COST);
        staticText = "spells your opponents cast that target you or a permanent you control cost {2} more to cast";
    }

    private MonasterySiegeCostIncreaseEffect(final MonasterySiegeCostIncreaseEffect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game, Ability source, Ability abilityToModify) {
        CardUtil.increaseCost(abilityToModify, 2);
        return true;
    }

    @Override
    public boolean applies(Ability abilityToModify, Ability source, Game game) {
        if (!(abilityToModify instanceof SpellAbility)) {
            return false;
        }

        if (!game.getOpponents(source.getControllerId()).contains(abilityToModify.getControllerId())) {
            return false;
        }

        Spell spell = (Spell) game.getStack().getStackObject(abilityToModify.getId());
        Set<UUID> allTargets;
        if (spell != null) {
            // real cast
            allTargets = CardUtil.getAllSelectedTargets(abilityToModify, game);
        } else {
            // playable
            allTargets = CardUtil.getAllPossibleTargets(abilityToModify, game);

            // can target without cost increase
            if (allTargets.stream().anyMatch(target -> !isTargetCompatible(target, source, game))) {
                return false;
            }
        }

        return allTargets.stream().anyMatch(target -> isTargetCompatible(target, source, game));
    }

    private boolean isTargetCompatible(UUID target, Ability source, Game game) {
        // target you or a permanent you control
        Player targetPlayer = game.getPlayer(target);
        if (targetPlayer != null && targetPlayer.getId().equals(source.getControllerId())) {
            return true;
        }

        Permanent targetPermanent = game.getPermanent(target);
        if (targetPermanent != null && targetPermanent.isControlledBy(source.getControllerId())) {
            return true;
        }

        return false;
    }

    @Override
    public MonasterySiegeCostIncreaseEffect copy() {
        return new MonasterySiegeCostIncreaseEffect(this);
    }
}
