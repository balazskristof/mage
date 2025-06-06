package mage.client.dialog;

import mage.abilities.Ability;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.effects.common.continuous.BecomesFaceDownCreatureEffect;
import mage.abilities.icon.*;
import mage.abilities.keyword.TransformAbility;
import mage.cards.*;
import mage.cards.decks.Deck;
import mage.cards.repository.CardInfo;
import mage.cards.repository.CardRepository;
import mage.cards.repository.ExpansionInfo;
import mage.cards.repository.ExpansionRepository;
import mage.client.MageFrame;
import mage.client.cards.BigCard;
import mage.client.game.PlayAreaPanel;
import mage.client.game.PlayerPanelExt;
import mage.client.themes.ThemeType;
import mage.client.util.ClientEventType;
import mage.client.util.Event;
import mage.client.util.GUISizeHelper;
import mage.client.util.Listener;
import mage.constants.MultiplayerAttackOption;
import mage.constants.RangeOfInfluence;
import mage.constants.Zone;
import mage.counters.Counter;
import mage.counters.CounterType;
import mage.designations.CitysBlessing;
import mage.designations.Monarch;
import mage.game.*;
import mage.game.command.Dungeon;
import mage.game.command.Emblem;
import mage.game.command.Plane;
import mage.game.match.*;
import mage.game.mulligan.Mulligan;
import mage.game.mulligan.MulliganType;
import mage.game.permanent.PermanentCard;
import mage.game.permanent.PermanentMeld;
import mage.game.permanent.PermanentToken;
import mage.game.permanent.token.IncubatorToken;
import mage.game.permanent.token.Phyrexian00Token;
import mage.game.permanent.token.Token;
import mage.game.permanent.token.ZombieToken;
import mage.players.Player;
import mage.players.StubPlayer;
import mage.util.CardUtil;
import mage.util.RandomUtil;
import mage.view.*;
import org.apache.log4j.Logger;
import org.mage.card.arcane.CardPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.*;

/**
 * App GUI: debug only, testing card renders and manipulations
 *
 * @author JayDi85
 */
public class TestCardRenderDialog extends MageDialog {

    private static final Logger logger = Logger.getLogger(TestCardRenderDialog.class);

    float cardSizeMod = 1.0f;
    float playerSizeMod = 1.0f;
    private Match match = null;
    private Game game = null;
    private BigCard bigCard = null;
    Listener<Event> cardListener = null;

    private PlayerPanelExt player = null; // original form lost, so can add only runtime

    public TestCardRenderDialog() {
        initComponents();
    }

    public void showDialog() {
        this.setModal(false);
        getRootPane().setDefaultButton(buttonCancel);

        // init render mode
        this.comboRenderMode.setSelectedIndex(PreferencesDialog.getRenderMode());

        // init themes list
        this.comboTheme.setModel(new DefaultComboBoxModel(ThemeType.values()));
        this.comboTheme.setSelectedItem(PreferencesDialog.getCurrentTheme());

        // init card icon colors list
        this.comboCardColor.setModel(new DefaultComboBoxModel(CardIconColor.values()));
        this.comboCardColor.setSelectedItem(CardIconColor.DEFAULT);

        // debug logs to show current component
        /*
        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
            public void eventDispatched(AWTEvent e) {
                logger.info("component: " + e.getSource());
            }
        }, AWTEvent.MOUSE_EVENT_MASK);
         */

        // init player panel
        player = new PlayerPanelExt();
        this.playerPanel.setLayout(new BorderLayout(5, 5));
        this.playerPanel.add(player, BorderLayout.CENTER);

        // render cards
        reloadCardsAndPlayer(false);

        // windows settings
        MageFrame.getDesktop().remove(this);
        MageFrame.getDesktop().add(this, this.isModal() ? JLayeredPane.MODAL_LAYER : JLayeredPane.PALETTE_LAYER);
        this.makeWindowCentered();

        // Close on "ESC"
        registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        this.setVisible(true);
    }

    private void onCancel() {
        this.removeDialog();
    }

    private PermanentView createPermanentCard(Game game, UUID controllerId, String code, String cardNumber, int powerBoosted, int toughnessBoosted, int damage, boolean tapped, boolean transform, List<Ability> extraAbilities) {
        CardInfo cardInfo = CardRepository.instance.findCard(code, cardNumber, false);
        ExpansionInfo setInfo = ExpansionRepository.instance.getSetByCode(code);
        CardSetInfo testSet = new CardSetInfo(cardInfo.getName(), setInfo.getCode(), cardNumber, cardInfo.getRarity(),
                new CardGraphicInfo(cardInfo.getFrameStyle(), cardInfo.usesVariousArt()));
        Card newCard = CardImpl.createCard(cardInfo.getClassName(), testSet);

        Set<Card> cardsList = new HashSet<>();
        cardsList.add(newCard);
        game.loadCards(cardsList, controllerId);

        Card permCard = CardUtil.getDefaultCardSideForBattlefield(game, newCard);
        if (extraAbilities != null) {
            extraAbilities.forEach(ability -> permCard.addAbility(ability));
        }

        // meld card must be a special class, see CardUtils.putCardOntoBattlefieldWithEffects
        PermanentCard permanent;
        if (permCard instanceof MeldCard) {
            permanent = new PermanentMeld(permCard, controllerId, game);
        } else {
            permanent = new PermanentCard(permCard, controllerId, game);
        }

        if (transform) {
            // need direct transform call to keep other side info (original)
            TransformAbility.transformPermanent(permanent, game, null);
        }

        if (damage > 0) permanent.damage(damage, controllerId, null, game);
        if (powerBoosted > 0) permanent.getPower().setBoostedValue(powerBoosted);
        if (toughnessBoosted > 0) permanent.getToughness().setBoostedValue(toughnessBoosted);
        permanent.removeSummoningSickness();
        permanent.setTapped(tapped);
        PermanentView cardView = new PermanentView(permanent, permCard, controllerId, game);

        return cardView;
    }

    private CardView createFaceDownCard(Game game, UUID controllerId, String code, String cardNumber, boolean isMorphed, boolean isManifested, boolean tapped) {
        CardInfo cardInfo = CardRepository.instance.findCard(code, cardNumber, false);
        ExpansionInfo setInfo = ExpansionRepository.instance.getSetByCode(code);
        CardSetInfo testSet = new CardSetInfo(cardInfo.getName(), setInfo.getCode(), cardNumber, cardInfo.getRarity(),
                new CardGraphicInfo(cardInfo.getFrameStyle(), cardInfo.usesVariousArt()));
        Card newCard = CardImpl.createCard(cardInfo.getClassName(), testSet);

        Set<Card> cardsList = new HashSet<>();
        cardsList.add(newCard);
        game.loadCards(cardsList, controllerId);

        Card permCard = CardUtil.getDefaultCardSideForBattlefield(game, newCard);

        PermanentCard perm = new PermanentCard(permCard, controllerId, game);
        perm.setFaceDown(true, game);
        perm.setMorphed(isMorphed);
        perm.setManifested(isManifested);
        perm.removeSummoningSickness();
        perm.setTapped(tapped);
        if (perm.isTransformable()) {
            perm.setTransformed(true);
        }

        // workaround to apply face down image and other settings
        if (perm.isFaceDown(game)) {
            BecomesFaceDownCreatureEffect.makeFaceDownObject(
                    game,
                    null,
                    perm,
                    BecomesFaceDownCreatureEffect.findFaceDownType(game, perm),
                    null
            );
        }

        PermanentView cardView = new PermanentView(perm, permCard, controllerId, game);
        cardView.setInViewerOnly(false); // must false for face down
        return cardView;
    }

    private CardView createHandCard(Game game, UUID controllerId, String code, String cardNumber) {
        CardInfo cardInfo = CardRepository.instance.findCard(code, cardNumber, false);
        ExpansionInfo setInfo = ExpansionRepository.instance.getSetByCode(code);
        CardSetInfo testSet = new CardSetInfo(cardInfo.getName(), setInfo.getCode(), cardNumber, cardInfo.getRarity(),
                new CardGraphicInfo(cardInfo.getFrameStyle(), cardInfo.usesVariousArt()));
        Card card = CardImpl.createCard(cardInfo.getClassName(), testSet);

        Set<Card> cardsList = new HashSet<>();
        cardsList.add(card);
        game.loadCards(cardsList, controllerId);

        CardView cardView = new CardView(card);
        return cardView;
    }

    private AbilityView createEmblem(Game game, Emblem emblem) {
        AbilityView emblemView = new AbilityView(emblem.getAbilities().get(0), emblem.getName(), new CardView(new EmblemView(emblem, game)));
        emblemView.setName(emblem.getName());
        return emblemView;
    }

    private AbilityView createDungeon(Game game, Dungeon dungeon) {
        AbilityView emblemView = new AbilityView(dungeon.getAbilities().get(0), dungeon.getName(), new CardView(new DungeonView(dungeon)));
        emblemView.setName(dungeon.getName());
        return emblemView;
    }

    private AbilityView createPlane(Game game, Plane plane) {
        AbilityView planeView = new AbilityView(plane.getAbilities().get(0), plane.getName(), new CardView(new PlaneView(plane, game)));
        planeView.setName(plane.getName());
        return planeView;
    }

    private CardView createToken(Game game, UUID controllerId, Token token, String code, int damage, boolean tapped, boolean transformed) {
        Token sourceToken = token.copy();
        sourceToken.setExpansionSetCode(code);

        PermanentToken perm = new PermanentToken(sourceToken, controllerId, game);
        Set<Card> cardsList = new HashSet<>();
        cardsList.add(perm);
        game.loadCards(cardsList, controllerId);

        if (damage > 0) perm.damage(damage, controllerId, null, game);
        perm.removeSummoningSickness();
        perm.setTapped(tapped);
        if (perm.isTransformable() && transformed) {
            perm.setTransformed(true);
        }
        PermanentView cardView = new PermanentView(perm, game.getCard(perm.getId()), controllerId, game);
        //cardView.setInViewerOnly(true); // ???
        return cardView;
    }

    private void reloadCardsAndPlayer(boolean refreshTheme) {
        // it contains GUI reload feature (must re-render all frames with new look and fill style),
        // so call it for theme combobox only
        // bug example: null errors on card size slider usage

        // apply selected theme (warning, it will be applied for all app, so can be bugged in other dialogs - but it's ok for debug)
        // bug example: render part of elements with old colors
        if (refreshTheme) {
            PreferencesDialog.setCurrentTheme((ThemeType) comboTheme.getSelectedItem());
        }

        // prepare fake game and players without real match
        // it's a workaround with minimum code and data init
        this.match = new FakeMatch();
        this.game = new FakeGame();
        Deck deck = new Deck();
        Player playerYou = new StubPlayer("player1", RangeOfInfluence.ALL);
        playerYou.addDesignation(new CitysBlessing());
        game.getState().setMonarchId(playerYou.getId());
        playerYou.addCounters(new Counter(CounterType.POISON.toString(), 10), playerYou.getId(), null, game);
        this.match.addPlayer(playerYou, deck);
        this.game.addPlayer(playerYou, deck);
        Player playerOpponent = new StubPlayer("player2", RangeOfInfluence.ALL);
        this.game.addPlayer(playerOpponent, deck);
        this.match.addPlayer(playerOpponent, deck);
        this.bigCard = new BigCard();

        // update PLAYER

        // active status (it's already reset to default, e.g. active)
        switch ((String) Objects.requireNonNull(comboPlayerStatus.getSelectedItem())) {
            case "Active":
                this.game.getState().setActivePlayerId(playerYou.getId());
                break;
            case "Inactive":
                this.game.getState().setActivePlayerId(playerOpponent.getId());
                break;
            case "Dead":
                playerYou.leave();
                this.game.getState().setActivePlayerId(playerOpponent.getId());
                break;
            default:
                throw new IllegalArgumentException("Unknown control type");
        }

        // control type
        boolean isMe;
        UUID controlledId;
        switch ((String) Objects.requireNonNull(comboPlayerController.getSelectedItem())) {
            case "me":
                isMe = true;
                controlledId = playerYou.getId();
                break;
            case "opponent":
                isMe = false;
                controlledId = playerOpponent.getId();
                break;
            default:
                throw new IllegalArgumentException("Unknown control type");
        }

        // size mode
        boolean smallMode = checkPlayerSmallMode.isSelected();

        // possible target
        Set<UUID> possibleTargets = null;
        if (checkPlayerAsTarget.isSelected()) {
            possibleTargets = new LinkedHashSet<>();
            possibleTargets.add(playerYou.getId());
        }

        // chosen target
        Set<UUID> chosenTargets = null;
        if (false) { // TODO: add GUI's checkbox for checkPlayerAsChosen
            chosenTargets = new LinkedHashSet<>();
            chosenTargets.add(playerYou.getId());
        }

        // player's panel
        if (this.player == null) {
            // create new panel
            this.playerPanel.setLayout(new BorderLayout(5, 5));
            this.player = new PlayerPanelExt(this.playerSizeMod);
            this.playerPanel.add(player, BorderLayout.CENTER);
        } else {
            // update existing panel (recreate all inner components)
            this.player.fullRefresh(this.playerSizeMod);
        }
        this.playerPanel.setPreferredSize(new java.awt.Dimension(Math.round(100 * this.playerSizeMod), 10));
        // update data in player's panel
        GameView gameView = new GameView(this.game.getState(), this.game, controlledId, null);
        PlayerView currentPlayerView = gameView.getPlayers()
                .stream()
                .filter(p -> p.getPlayerId().equals(playerYou.getId()))
                .findFirst()
                .orElse(null);
        this.player.init(this.game.getId(), playerYou.getId(), isMe, this.bigCard, 0);
        this.player.update(gameView, currentPlayerView, possibleTargets, chosenTargets);
        this.player.sizePlayerPanel(smallMode);

        // update CARDS

        cardsPanel.cleanUp();
        cardsPanel.setCustomRenderMode(comboRenderMode.getSelectedIndex());
        cardsPanel.setCustomNeedFullPermanentRender(true); // enable full battlefield render mode (it was bugged in test dialog so was disabled in old days, not it works fine)
        cardsPanel.setCustomCardSize(new Dimension(getCardWidth(), getCardHeight()));
        cardsPanel.setCustomXOffsetBetweenCardsOrColumns(10);
        cardsPanel.setCustomCardIconsPanelPosition(CardIconPosition.fromString((String) comboCardIconsPosition.getSelectedItem()));
        cardsPanel.setCustomCardIconsPanelOrder(CardIconOrder.fromString((String) comboCardIconsOrder.getSelectedItem()));
        cardsPanel.setCustomCardIconsPanelColor((CardIconColor) comboCardColor.getSelectedItem());
        cardsPanel.setCustomCardIconsMaxVisibleCount((Integer) spinnerCardIconsMaxVisible.getValue());
        int needAdditionalIcons = Math.min(99, Math.max(0, (Integer) spinnerCardIconsAdditionalAmount.getValue()));

        // reload new settings
        cardsPanel.changeGUISize();

        // sample popup menus
        JMenuItem item;
        JPopupMenu popupCardMenu = new JPopupMenu();
        item = new JMenuItem("Card menu 1");
        popupCardMenu.add(item);
        item = new JMenuItem("Card menu 2");
        popupCardMenu.add(item);
        item = new JMenuItem("Card menu 3");
        popupCardMenu.add(item);
        //
        JPopupMenu popupPanelMenu = new JPopupMenu();
        item = new JMenuItem("Panel menu 1");
        popupPanelMenu.add(item);
        item = new JMenuItem("Panel menu 2");
        popupPanelMenu.add(item);
        item = new JMenuItem("Panel menu 3");
        popupPanelMenu.add(item);

        // init card listener for clicks, menu and other events
        if (this.cardListener == null) {
            this.cardListener = event -> {
                switch (event.getEventType()) {
                    case CARD_CLICK:
                    case CARD_DOUBLE_CLICK:
                        handleCardClick(event);
                        break;
                    case CARD_POPUP_MENU:
                        if (event.getSource() != null) {
                            // card
                            handlePopupMenu(event, popupCardMenu);
                        } else {
                            // panel
                            handlePopupMenu(event, popupPanelMenu);
                        }
                        break;
                }
            };
            cardsPanel.addCardEventListener(this.cardListener);
        }

        List<Ability> additionalIcons = Collections.singletonList(new SimpleStaticAbility(Zone.ALL, null));
        for (int i = 0; i < needAdditionalIcons; i++) {
            String text = "";
            if (RandomUtil.nextBoolean()) {
                if (RandomUtil.nextBoolean()) {
                    text = "75";
                } else {
                    text = "8";
                }
            }
            additionalIcons.get(0).addIcon(new CardIconImpl(CardIconType.PLAYABLE_COUNT, "test icon " + i + 1, text));
        }

        List<CardView> cardViews = new ArrayList<>();

        //* test face down
        cardViews.add(createPermanentCard(game, playerYou.getId(), "RNA", "263", 0, 0, 0, false, false, null)); // mountain
        cardViews.add(createPermanentCard(game, playerYou.getId(), "RNA", "185", 0, 0, 0, true, false, null)); // Judith, the Scourge Diva
        cardViews.add(createHandCard(game, playerYou.getId(), "DIS", "153")); // Odds // Ends (split card)
        cardViews.add(createHandCard(game, playerYou.getId(), "ELD", "38")); // Animating Faerie (adventure card)
        cardViews.add(createFaceDownCard(game, playerOpponent.getId(), "ELD", "38", false, false, false)); // face down
        cardViews.add(createFaceDownCard(game, playerOpponent.getId(), "ELD", "38", true, false, true)); // morphed
        cardViews.add(createFaceDownCard(game, playerOpponent.getId(), "ELD", "38", false, true, false)); // manifested
        //*/

        /* test emblems
        cardViews.add(createPermanentCard(game, playerYou.getId(), "RNA", "78", 125, 89, 0, false, false, null)); // Noxious Groodion
        cardViews.add(createPermanentCard(game, playerYou.getId(), "RNA", "14", 3, 5, 2, false, false, null)); // Knight of Sorrows
        cardViews.add(createPermanentCard(game, playerYou.getId(), "DKA", "140", 5, 2, 2, false, false, null)); // Huntmaster of the Fells, transforms
        cardViews.add(createPermanentCard(game, playerYou.getId(), "RNA", "221", 0, 0, 0, false, false, null)); // Bedeck // Bedazzle
        cardViews.add(createPermanentCard(game, playerYou.getId(), "XLN", "234", 0, 0, 0, false, false, null)); // Conqueror's Galleon
        cardViews.add(createEmblem(new AjaniAdversaryOfTyrantsEmblem())); // Emblem Ajani
        cardViews.add(createPlane(new AkoumPlane())); // Plane - Akoum
        //*/

        /* test split, transform and mdf in hands
        cardViews.add(createHandCard(game, playerYou.getId(), "SOI", "97")); // Accursed Witch
        cardViews.add(createHandCard(game, playerYou.getId(), "ZNR", "134")); // Akoum Warrior
        cardViews.add(createHandCard(game, playerYou.getId(), "UMA", "225")); // Fire // Ice
        cardViews.add(createHandCard(game, playerYou.getId(), "DGM", "123")); // Beck // Call
        cardViews.add(createHandCard(game, playerYou.getId(), "AKH", "210")); // Dusk // Dawn
        //*/

        /* test adventure cards in hands
        cardViews.add(createHandCard(game, playerYou.getId(), "ELD", "14")); // Giant Killer
        cardViews.add(createHandCard(game, playerYou.getId(), "WOE", "222")); // Cruel Somnophage
        cardViews.add(createHandCard(game, playerYou.getId(), "WOE", "227")); // Gingerbread Hunter
        cardViews.add(createHandCard(game, playerYou.getId(), "WOE", "221")); // Callous Sell-Sword
        cardViews.add(createHandCard(game, playerYou.getId(), "ELD", "149")); // Beanstalk Giant
        cardViews.add(createHandCard(game, playerYou.getId(), "WOE", "220")); // Beluna Grandsquall
        //*/

        /* test saga and case cards in hands
        cardViews.add(createHandCard(game, playerYou.getId(), "DOM", "90")); // The Eldest Reborn
        cardViews.add(createHandCard(game, playerYou.getId(), "MH2", "259")); // Urza's Saga
        cardViews.add(createHandCard(game, playerYou.getId(), "MKM", "113")); // Case of the Burning Masks
        cardViews.add(createHandCard(game, playerYou.getId(), "MKM", "155")); // Case of the Locked Hothouse
        //*/

        /* test case, class and saga cards in hands
        cardViews.add(createHandCard(game, playerYou.getId(), "MKM", "113")); // Case of the Burning Masks
        cardViews.add(createHandCard(game, playerYou.getId(), "MKM", "155")); // Case of the Locked Hothouse
        cardViews.add(createHandCard(game, playerYou.getId(), "AFR", "6")); // Cleric Class
        cardViews.add(createHandCard(game, playerYou.getId(), "AFR", "230")); // Rogue Class
        cardViews.add(createHandCard(game, playerYou.getId(), "DOM", "90")); // The Eldest Reborn
        cardViews.add(createHandCard(game, playerYou.getId(), "MH2", "259")); // Urza's Saga
        //*/

        /* test meld cards in hands and battlefield
        cardViews.add(createHandCard(game, playerYou.getId(), "EMN", "204")); // Hanweir Battlements
        cardViews.add(createHandCard(game, playerYou.getId(), "EMN", "130a")); // Hanweir Garrison
        cardViews.add(createHandCard(game, playerYou.getId(), "EMN", "130b")); // Hanweir, the Writhing Township
        cardViews.add(createPermanentCard(game, playerYou.getId(), "EMN", "204", 1, 1, 0, false, false, null)); // Hanweir Battlements
        cardViews.add(createPermanentCard(game, playerYou.getId(), "EMN", "130a", 1, 1, 0, false, false, null)); // Hanweir Garrison
        cardViews.add(createPermanentCard(game, playerYou.getId(), "EMN", "130b", 1, 1, 0, false, false, null)); // Hanweir, the Writhing Township
        //*/

        /* test variant double faced cards (main and second sides must be same pair)
        // Jacob Hauken, Inspector -> Hauken's Insight
        cardViews.add(createHandCard(game, playerYou.getId(), "VOW", "65"));
        cardViews.add(createHandCard(game, playerYou.getId(), "VOW", "320"));
        cardViews.add(createHandCard(game, playerYou.getId(), "VOW", "332"));
        cardViews.add(createPermanentCard(game, playerYou.getId(), "VOW", "65", 1, 1, 0, false, false, null));
        cardViews.add(createPermanentCard(game, playerYou.getId(), "VOW", "320", 1, 1, 0, false, false, null));
        cardViews.add(createPermanentCard(game, playerYou.getId(), "VOW", "332", 1, 1, 0, false, false, null));
        //*/

        /* test card icons
        cardViews.add(createHandCard(game, playerYou.getId(), "POR", "169")); // Grizzly Bears
        cardViews.add(createHandCard(game, playerYou.getId(), "DKA", "140")); // Huntmaster of the Fells, transforms
        cardViews.add(createPermanentCard(game, playerYou.getId(), "DKA", "140", 3, 3, 1, false, true, additionalIcons)); // Huntmaster of the Fells, transforms
        cardViews.add(createPermanentCard(game, playerYou.getId(), "MB1", "401", 1, 1, 0, false, false, additionalIcons)); // Hinterland Drake
        //cardViews.add(createPermanentCard(game, playerYou.getId(), "MB1", "1441", 1, 1, 0, true, false, additionalIcons)); // Kathari Remnant
        //cardViews.add(createPermanentCard(game, playerYou.getId(), "KHM", "50", 1, 1, 0, true, false, additionalIcons)); // Cosima, God of the Voyage
        //*/

        /* test tokens
        // normal
        cardViews.add(createToken(game, playerYou.getId(), new ZombieToken(), "10E", 0, false, false));
        cardViews.add(createToken(game, playerYou.getId(), new ZombieToken(), "XXX", 1, false, false));
        cardViews.add(createToken(game, playerYou.getId(), new ZombieToken(), null, 0, false, false));
        // double faced
        cardViews.add(createToken(game, playerYou.getId(), new IncubatorToken(), "MOM", 0, false, false));
        cardViews.add(createToken(game, playerYou.getId(), new Phyrexian00Token(), "MOM", 1, false, true));
        //*/

        // duplicate cards
        if (checkBoxGenerateManyCards.isSelected()) {
            while (cardViews.size() < 30) {
                int addingCount = cardViews.size();
                for (int i = 0; i < addingCount; i++) {
                    CardView view = cardViews.get(i);
                    CardView newView = new CardView(view);
                    cardViews.add(newView);
                }
            }
        }

        CardsView view = new CardsView(cardViews);
        cardsPanel.loadCards(view, this.bigCard, this.game.getId());
    }

    private void handleCardClick(Event event) {
        MageCard panel = (MageCard) event.getComponent();
        if (event.getEventType() == ClientEventType.CARD_DOUBLE_CLICK) {
            // card tap
            if (panel.getMainPanel() instanceof CardPanel) {
                CardPanel main = (CardPanel) panel.getMainPanel();
                if (main.getGameCard() instanceof PermanentView) {
                    // new settings must be as a new copy -- it would activate the animations
                    PermanentView oldPermanent = (PermanentView) main.getGameCard();
                    PermanentView newPermament = new PermanentView(
                            oldPermanent,
                            game.getCard(oldPermanent.getId()),
                            UUID.randomUUID(),
                            game
                    );
                    newPermament.overrideTapped(!oldPermanent.isTapped());
                    main.update(newPermament);
                }
            }
        } else {
            // card choose
            panel.setChoosable(!panel.isChoosable());
        }
        cardsPanel.redraw();
    }

    private void handlePopupMenu(Event event, JPopupMenu popupMenu) {
        //Point p = event.getComponent().getLocationOnScreen();
        Point p = MouseInfo.getPointerInfo().getLocation();
        popupMenu.show(this, 0, 0); // use relative coords
        popupMenu.setLocation(p); // use screen coords
    }

    private int getCardWidth() {
        if (GUISizeHelper.editorCardDimension == null) {
            return 200;
        }
        return (int) (GUISizeHelper.editorCardDimension.width * cardSizeMod);
    }

    private int getCardHeight() {
        return (int) (1.4 * getCardWidth());
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonCancel = new javax.swing.JButton();
        buttonReloadCards = new javax.swing.JButton();
        labelRenderMode = new javax.swing.JLabel();
        comboRenderMode = new javax.swing.JComboBox<>();
        sliderSize = new javax.swing.JSlider();
        labelSize = new javax.swing.JLabel();
        checkBoxGenerateManyCards = new javax.swing.JCheckBox();
        panelCardIcons = new javax.swing.JPanel();
        labelCardIconsPosition = new javax.swing.JLabel();
        comboCardIconsPosition = new javax.swing.JComboBox<>();
        labelCardIconsMaxVisible = new javax.swing.JLabel();
        spinnerCardIconsMaxVisible = new javax.swing.JSpinner();
        labelCardIconsAdditionalAmount = new javax.swing.JLabel();
        spinnerCardIconsAdditionalAmount = new javax.swing.JSpinner();
        labelCardIconsOrder = new javax.swing.JLabel();
        comboCardIconsOrder = new javax.swing.JComboBox<>();
        labelTheme = new javax.swing.JLabel();
        comboTheme = new javax.swing.JComboBox<>();
        labelCardColor = new javax.swing.JLabel();
        comboCardColor = new javax.swing.JComboBox<>();
        panelBattlefield = new javax.swing.JPanel();
        cardsPanel = new mage.client.cards.CardArea();
        otherPanel = new javax.swing.JPanel();
        playerPanel = new javax.swing.JPanel();
        playerOptions = new javax.swing.JPanel();
        checkPlayerSmallMode = new javax.swing.JCheckBox();
        comboPlayerStatus = new javax.swing.JComboBox<>();
        comboPlayerController = new javax.swing.JComboBox<>();
        checkPlayerAsTarget = new javax.swing.JCheckBox();

        setResizable(true);

        buttonCancel.setText("Close");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        buttonReloadCards.setText("Reload cards");
        buttonReloadCards.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonReloadCardsActionPerformed(evt);
            }
        });

        labelRenderMode.setText("Render mode:");

        comboRenderMode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MTGO", "Image" }));
        comboRenderMode.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboRenderModeItemStateChanged(evt);
            }
        });

        sliderSize.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderSizeStateChanged(evt);
            }
        });

        labelSize.setText("Card size:");

        checkBoxGenerateManyCards.setText("Generate many cards");
        checkBoxGenerateManyCards.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkBoxGenerateManyCardsItemStateChanged(evt);
            }
        });

        labelCardIconsPosition.setText("Card icons position:");

        comboCardIconsPosition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TOP", "LEFT", "RIGHT", "BOTTOM", "CORNER_TOP_LEFT", "CORNER_TOP_RIGHT", "CORNER_BOTTOM_LEFT", "CORNER_BOTTOM_RIGHT" }));
        comboCardIconsPosition.setSelectedIndex(1);
        comboCardIconsPosition.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboCardIconsPositionItemStateChanged(evt);
            }
        });

        labelCardIconsMaxVisible.setText("Max visible:");

        spinnerCardIconsMaxVisible.setValue(3);
        spinnerCardIconsMaxVisible.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnerCardIconsMaxVisibleStateChanged(evt);
            }
        });

        labelCardIconsAdditionalAmount.setText("Add additional icons:");

        spinnerCardIconsAdditionalAmount.setValue(10);
        spinnerCardIconsAdditionalAmount.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnerCardIconsAdditionalAmountStateChanged(evt);
            }
        });

        labelCardIconsOrder.setText("Order:");

        comboCardIconsOrder.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "START", "CENTER", "END" }));
        comboCardIconsOrder.setSelectedIndex(2);
        comboCardIconsOrder.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboCardIconsOrderItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout panelCardIconsLayout = new javax.swing.GroupLayout(panelCardIcons);
        panelCardIcons.setLayout(panelCardIconsLayout);
        panelCardIconsLayout.setHorizontalGroup(
            panelCardIconsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCardIconsLayout.createSequentialGroup()
                .addComponent(labelCardIconsPosition)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboCardIconsPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelCardIconsOrder)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboCardIconsOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelCardIconsMaxVisible)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinnerCardIconsMaxVisible, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelCardIconsAdditionalAmount)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinnerCardIconsAdditionalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelCardIconsLayout.setVerticalGroup(
            panelCardIconsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCardIconsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(comboCardIconsPosition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(labelCardIconsPosition)
                .addComponent(labelCardIconsMaxVisible)
                .addComponent(spinnerCardIconsMaxVisible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(labelCardIconsAdditionalAmount)
                .addComponent(spinnerCardIconsAdditionalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(labelCardIconsOrder)
                .addComponent(comboCardIconsOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        labelTheme.setText("Theme:");

        comboTheme.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "loading..." }));
        comboTheme.setToolTipText("WARNING, selected theme will be applied to full app, not render dialog only");
        comboTheme.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboThemeItemStateChanged(evt);
            }
        });

        labelCardColor.setText("Card color:");

        comboCardColor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "loading..." }));
        comboCardColor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboCardColorItemStateChanged(evt);
            }
        });

        panelBattlefield.setLayout(new java.awt.BorderLayout());
        panelBattlefield.add(cardsPanel, java.awt.BorderLayout.CENTER);

        otherPanel.setLayout(new java.awt.BorderLayout());

        playerPanel.setPreferredSize(new java.awt.Dimension(100, 10));

        javax.swing.GroupLayout playerPanelLayout = new javax.swing.GroupLayout(playerPanel);
        playerPanel.setLayout(playerPanelLayout);
        playerPanelLayout.setHorizontalGroup(
            playerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        playerPanelLayout.setVerticalGroup(
            playerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        otherPanel.add(playerPanel, java.awt.BorderLayout.CENTER);

        playerOptions.setLayout(new javax.swing.BoxLayout(playerOptions, javax.swing.BoxLayout.Y_AXIS));

        checkPlayerSmallMode.setText("Small mode");
        checkPlayerSmallMode.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkPlayerSmallModeItemStateChanged(evt);
            }
        });
        playerOptions.add(checkPlayerSmallMode);

        comboPlayerStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Inactive", "Dead" }));
        comboPlayerStatus.setAlignmentX(0.0F);
        comboPlayerStatus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboPlayerStatusItemStateChanged(evt);
            }
        });
        playerOptions.add(comboPlayerStatus);

        comboPlayerController.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "me", "opponent" }));
        comboPlayerController.setAlignmentX(0.0F);
        comboPlayerController.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboPlayerControllerItemStateChanged(evt);
            }
        });
        playerOptions.add(comboPlayerController);

        checkPlayerAsTarget.setText("As possible target");
        checkPlayerAsTarget.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkPlayerAsTargetItemStateChanged(evt);
            }
        });
        playerOptions.add(checkPlayerAsTarget);

        otherPanel.add(playerOptions, java.awt.BorderLayout.NORTH);

        panelBattlefield.add(otherPanel, java.awt.BorderLayout.WEST);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelCardIcons, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonReloadCards)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelRenderMode)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboRenderMode, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelTheme)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboTheme, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelCardColor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboCardColor, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelSize)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sliderSize, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkBoxGenerateManyCards)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(panelBattlefield, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboCardColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelCardColor))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonReloadCards)
                            .addComponent(labelRenderMode)
                            .addComponent(comboRenderMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSize)
                            .addComponent(comboTheme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelTheme)))
                    .addComponent(sliderSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkBoxGenerateManyCards))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelCardIcons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBattlefield, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        onCancel();
    }//GEN-LAST:event_buttonCancelActionPerformed

    private void buttonReloadCardsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonReloadCardsActionPerformed
        reloadCardsAndPlayer(true);
    }//GEN-LAST:event_buttonReloadCardsActionPerformed

    private void comboRenderModeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboRenderModeItemStateChanged
        // render modes are loading on show dialog, so must ignore change event on startup
        if (this.isVisible()) {
            reloadCardsAndPlayer(false);
        }
    }//GEN-LAST:event_comboRenderModeItemStateChanged

    private void sliderSizeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderSizeStateChanged
        // from DragCardGrid
        // Fraction in [-1, 1]
        float sliderFrac = ((float) (sliderSize.getValue() - 50)) / 50;
        // Convert to frac in [0.5, 2.0] exponentially
        cardSizeMod = (float) Math.pow(2, sliderFrac);
        playerSizeMod = (float) Math.pow(2, sliderFrac);
        reloadCardsAndPlayer(false);
    }//GEN-LAST:event_sliderSizeStateChanged

    private void checkBoxGenerateManyCardsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkBoxGenerateManyCardsItemStateChanged
        reloadCardsAndPlayer(false);
    }//GEN-LAST:event_checkBoxGenerateManyCardsItemStateChanged

    private void comboCardIconsPositionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboCardIconsPositionItemStateChanged
        reloadCardsAndPlayer(false);
    }//GEN-LAST:event_comboCardIconsPositionItemStateChanged

    private void spinnerCardIconsMaxVisibleStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerCardIconsMaxVisibleStateChanged
        reloadCardsAndPlayer(false);
    }//GEN-LAST:event_spinnerCardIconsMaxVisibleStateChanged

    private void spinnerCardIconsAdditionalAmountStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerCardIconsAdditionalAmountStateChanged
        reloadCardsAndPlayer(false);
    }//GEN-LAST:event_spinnerCardIconsAdditionalAmountStateChanged

    private void comboCardIconsOrderItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboCardIconsOrderItemStateChanged
        reloadCardsAndPlayer(false);
    }//GEN-LAST:event_comboCardIconsOrderItemStateChanged

    private void comboThemeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboThemeItemStateChanged
        // themes list are loading on show dialog, so must ignore change event on startup
        if (this.isVisible()) {
            reloadCardsAndPlayer(true);
        }
    }//GEN-LAST:event_comboThemeItemStateChanged

    private void comboCardColorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboCardColorItemStateChanged
        // card icon colors list are loading on show dialog, so must ignore change event on startup
        if (this.isVisible()) {
            reloadCardsAndPlayer(false);
        }
    }//GEN-LAST:event_comboCardColorItemStateChanged

    private void comboPlayerStatusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboPlayerStatusItemStateChanged
        reloadCardsAndPlayer(false);
    }//GEN-LAST:event_comboPlayerStatusItemStateChanged

    private void comboPlayerControllerItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboPlayerControllerItemStateChanged
        reloadCardsAndPlayer(false);
    }//GEN-LAST:event_comboPlayerControllerItemStateChanged

    private void checkPlayerSmallModeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkPlayerSmallModeItemStateChanged
        reloadCardsAndPlayer(false);
    }//GEN-LAST:event_checkPlayerSmallModeItemStateChanged

    private void checkPlayerAsTargetItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkPlayerAsTargetItemStateChanged
        reloadCardsAndPlayer(false);
    }//GEN-LAST:event_checkPlayerAsTargetItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonReloadCards;
    private mage.client.cards.CardArea cardsPanel;
    private javax.swing.JCheckBox checkBoxGenerateManyCards;
    private javax.swing.JCheckBox checkPlayerAsTarget;
    private javax.swing.JCheckBox checkPlayerSmallMode;
    private javax.swing.JComboBox<String> comboCardColor;
    private javax.swing.JComboBox<String> comboCardIconsOrder;
    private javax.swing.JComboBox<String> comboCardIconsPosition;
    private javax.swing.JComboBox<String> comboPlayerController;
    private javax.swing.JComboBox<String> comboPlayerStatus;
    private javax.swing.JComboBox<String> comboRenderMode;
    private javax.swing.JComboBox<String> comboTheme;
    private javax.swing.JLabel labelCardColor;
    private javax.swing.JLabel labelCardIconsAdditionalAmount;
    private javax.swing.JLabel labelCardIconsMaxVisible;
    private javax.swing.JLabel labelCardIconsOrder;
    private javax.swing.JLabel labelCardIconsPosition;
    private javax.swing.JLabel labelRenderMode;
    private javax.swing.JLabel labelSize;
    private javax.swing.JLabel labelTheme;
    private javax.swing.JPanel otherPanel;
    private javax.swing.JPanel panelBattlefield;
    private javax.swing.JPanel panelCardIcons;
    private javax.swing.JPanel playerOptions;
    private javax.swing.JPanel playerPanel;
    private javax.swing.JSlider sliderSize;
    private javax.swing.JSpinner spinnerCardIconsAdditionalAmount;
    private javax.swing.JSpinner spinnerCardIconsMaxVisible;
    // End of variables declaration//GEN-END:variables
}