
package mage.sets;

import mage.cards.ExpansionSet;
import mage.constants.Rarity;
import mage.constants.SetType;

/**
 * @author Susucr
 */
public final class TheLostCavernsOfIxalanCommander extends ExpansionSet {

    private static final TheLostCavernsOfIxalanCommander instance = new TheLostCavernsOfIxalanCommander();

    public static TheLostCavernsOfIxalanCommander getInstance() {
        return instance;
    }

    private TheLostCavernsOfIxalanCommander() {
        super("The Lost Caverns of Ixalan Commander", "LCC", ExpansionSet.buildDate(2023, 11, 17), SetType.SUPPLEMENTAL);
        this.hasBoosters = false;
        this.hasBasicLands = false;

        cards.add(new SetCardInfo("Admiral Beckett Brass", 264, Rarity.MYTHIC, mage.cards.a.AdmiralBeckettBrass.class));
        cards.add(new SetCardInfo("Admiral Brass, Unsinkable", 1, Rarity.MYTHIC, mage.cards.a.AdmiralBrassUnsinkable.class));
        cards.add(new SetCardInfo("Aetherize", 142, Rarity.UNCOMMON, mage.cards.a.Aetherize.class));
        cards.add(new SetCardInfo("Akroma's Will", 125, Rarity.RARE, mage.cards.a.AkromasWill.class));
        cards.add(new SetCardInfo("Alchemist's Refuge", 318, Rarity.RARE, mage.cards.a.AlchemistsRefuge.class));
        cards.add(new SetCardInfo("Altar of the Wretched", 10, Rarity.RARE, mage.cards.a.AltarOfTheWretched.class));
        cards.add(new SetCardInfo("Amphin Mutineer", 143, Rarity.RARE, mage.cards.a.AmphinMutineer.class));
        cards.add(new SetCardInfo("Amulet of Vigor", 103, Rarity.RARE, mage.cards.a.AmuletOfVigor.class));
        cards.add(new SetCardInfo("Angrath's Marauders", 215, Rarity.RARE, mage.cards.a.AngrathsMarauders.class));
        cards.add(new SetCardInfo("Apex Altisaur", 232, Rarity.RARE, mage.cards.a.ApexAltisaur.class));
        cards.add(new SetCardInfo("Apex Observatory", 15, Rarity.MYTHIC, mage.cards.a.ApexObservatory.class));
        cards.add(new SetCardInfo("Arcane Signet", 104, Rarity.UNCOMMON, mage.cards.a.ArcaneSignet.class));
        cards.add(new SetCardInfo("Arch of Orazca", 319, Rarity.RARE, mage.cards.a.ArchOfOrazca.class));
        cards.add(new SetCardInfo("Archaeomancer's Map", 101, Rarity.RARE, mage.cards.a.ArchaeomancersMap.class));
        cards.add(new SetCardInfo("Arm-Mounted Anchor", 67, Rarity.RARE, mage.cards.a.ArmMountedAnchor.class));
        cards.add(new SetCardInfo("Atzocan Seer", 265, Rarity.UNCOMMON, mage.cards.a.AtzocanSeer.class));
        cards.add(new SetCardInfo("Austere Command", 126, Rarity.RARE, mage.cards.a.AustereCommand.class));
        cards.add(new SetCardInfo("Azure Fleet Admiral", 144, Rarity.COMMON, mage.cards.a.AzureFleetAdmiral.class));
        cards.add(new SetCardInfo("Beast Within", 233, Rarity.UNCOMMON, mage.cards.b.BeastWithin.class));
        cards.add(new SetCardInfo("Bellowing Aegisaur", 127, Rarity.UNCOMMON, mage.cards.b.BellowingAegisaur.class));
        cards.add(new SetCardInfo("Benthic Biomancer", 145, Rarity.RARE, mage.cards.b.BenthicBiomancer.class));
        cards.add(new SetCardInfo("Bident of Thassa", 146, Rarity.RARE, mage.cards.b.BidentOfThassa.class));
        cards.add(new SetCardInfo("Black Market Connections", 181, Rarity.RARE, mage.cards.b.BlackMarketConnections.class));
        cards.add(new SetCardInfo("Blade of the Bloodchief", 300, Rarity.RARE, mage.cards.b.BladeOfTheBloodchief.class));
        cards.add(new SetCardInfo("Blasphemous Act", 216, Rarity.RARE, mage.cards.b.BlasphemousAct.class));
        cards.add(new SetCardInfo("Blood Artist", 182, Rarity.UNCOMMON, mage.cards.b.BloodArtist.class));
        cards.add(new SetCardInfo("Blood Money", 183, Rarity.MYTHIC, mage.cards.b.BloodMoney.class));
        cards.add(new SetCardInfo("Bloodghast", 184, Rarity.RARE, mage.cards.b.Bloodghast.class));
        cards.add(new SetCardInfo("Bloodline Necromancer", 185, Rarity.UNCOMMON, mage.cards.b.BloodlineNecromancer.class));
        cards.add(new SetCardInfo("Bloodtracker", 186, Rarity.RARE, mage.cards.b.Bloodtracker.class));
        cards.add(new SetCardInfo("Bojuka Bog", 320, Rarity.COMMON, mage.cards.b.BojukaBog.class));
        cards.add(new SetCardInfo("Branching Evolution", 234, Rarity.RARE, mage.cards.b.BranchingEvolution.class));
        cards.add(new SetCardInfo("Breeches, Brazen Plunderer", 217, Rarity.UNCOMMON, mage.cards.b.BreechesBrazenPlunderer.class));
        cards.add(new SetCardInfo("Broadside Bombardiers", 86, Rarity.RARE, mage.cards.b.BroadsideBombardiers.class));
        cards.add(new SetCardInfo("Bronzebeak Foragers", 69, Rarity.RARE, mage.cards.b.BronzebeakForagers.class));
        cards.add(new SetCardInfo("Butcher of Malakir", 187, Rarity.RARE, mage.cards.b.ButcherOfMalakir.class));
        cards.add(new SetCardInfo("Bygone Marvels", 89, Rarity.RARE, mage.cards.b.BygoneMarvels.class));
        cards.add(new SetCardInfo("Canopy Vista", 321, Rarity.RARE, mage.cards.c.CanopyVista.class));
        cards.add(new SetCardInfo("Captain Lannery Storm", 218, Rarity.RARE, mage.cards.c.CaptainLanneryStorm.class));
        cards.add(new SetCardInfo("Captivating Crew", 219, Rarity.RARE, mage.cards.c.CaptivatingCrew.class));
        cards.add(new SetCardInfo("Carmen, Cruel Skymarcher", 5, Rarity.MYTHIC, mage.cards.c.CarmenCruelSkymarcher.class));
        cards.add(new SetCardInfo("Chalice of the Void", 105, Rarity.MYTHIC, mage.cards.c.ChaliceOfTheVoid.class));
        cards.add(new SetCardInfo("Champion of Dusk", 188, Rarity.RARE, mage.cards.c.ChampionOfDusk.class));
        cards.add(new SetCardInfo("Chandra's Ignition", 220, Rarity.RARE, mage.cards.c.ChandrasIgnition.class));
        cards.add(new SetCardInfo("Chaos Warp", 221, Rarity.RARE, mage.cards.c.ChaosWarp.class));
        cards.add(new SetCardInfo("Charismatic Conqueror", 70, Rarity.RARE, mage.cards.c.CharismaticConqueror.class));
        cards.add(new SetCardInfo("Chimil, the Inner Sun", 106, Rarity.MYTHIC, mage.cards.c.ChimilTheInnerSun.class));
        cards.add(new SetCardInfo("Choked Estuary", 322, Rarity.RARE, mage.cards.c.ChokedEstuary.class));
        cards.add(new SetCardInfo("Chromatic Orrery", 107, Rarity.MYTHIC, mage.cards.c.ChromaticOrrery.class));
        cards.add(new SetCardInfo("Cinder Glade", 323, Rarity.RARE, mage.cards.c.CinderGlade.class));
        cards.add(new SetCardInfo("Clavileno, First of the Blessed", 2, Rarity.MYTHIC, mage.cards.c.ClavilenoFirstOfTheBlessed.class));
        cards.add(new SetCardInfo("Clifftop Retreat", 324, Rarity.RARE, mage.cards.c.ClifftopRetreat.class));
        cards.add(new SetCardInfo("Coat of Arms", 108, Rarity.RARE, mage.cards.c.CoatOfArms.class));
        cards.add(new SetCardInfo("Coercive Portal", 109, Rarity.MYTHIC, mage.cards.c.CoercivePortal.class));
        cards.add(new SetCardInfo("Coercive Recruiter", 222, Rarity.RARE, mage.cards.c.CoerciveRecruiter.class));
        cards.add(new SetCardInfo("Cold-Eyed Selkie", 266, Rarity.RARE, mage.cards.c.ColdEyedSelkie.class));
        cards.add(new SetCardInfo("Colossus Hammer", 110, Rarity.UNCOMMON, mage.cards.c.ColossusHammer.class));
        cards.add(new SetCardInfo("Command Tower", 325, Rarity.COMMON, mage.cards.c.CommandTower.class));
        cards.add(new SetCardInfo("Commander's Sphere", 301, Rarity.COMMON, mage.cards.c.CommandersSphere.class));
        cards.add(new SetCardInfo("Commit // Memory", 147, Rarity.RARE, mage.cards.c.CommitMemory.class));
        cards.add(new SetCardInfo("Contest of Claws", 12, Rarity.RARE, mage.cards.c.ContestOfClaws.class));
        cards.add(new SetCardInfo("Coralhelm Commander", 148, Rarity.RARE, mage.cards.c.CoralhelmCommander.class));
        cards.add(new SetCardInfo("Cordial Vampire", 189, Rarity.RARE, mage.cards.c.CordialVampire.class));
        cards.add(new SetCardInfo("Corsair Captain", 149, Rarity.RARE, mage.cards.c.CorsairCaptain.class));
        cards.add(new SetCardInfo("Cosmium Catalyst", 11, Rarity.RARE, mage.cards.c.CosmiumCatalyst.class));
        cards.add(new SetCardInfo("Crossway Troublemakers", 190, Rarity.RARE, mage.cards.c.CrosswayTroublemakers.class));
        cards.add(new SetCardInfo("Cruel Celebrant", 267, Rarity.UNCOMMON, mage.cards.c.CruelCelebrant.class));
        cards.add(new SetCardInfo("Crumbling Necropolis", 326, Rarity.UNCOMMON, mage.cards.c.CrumblingNecropolis.class));
        cards.add(new SetCardInfo("Cultivate", 235, Rarity.COMMON, mage.cards.c.Cultivate.class));
        cards.add(new SetCardInfo("Curious Altisaur", 90, Rarity.RARE, mage.cards.c.CuriousAltisaur.class));
        cards.add(new SetCardInfo("Curse of the Swine", 150, Rarity.RARE, mage.cards.c.CurseOfTheSwine.class));
        cards.add(new SetCardInfo("Damn", 191, Rarity.RARE, mage.cards.d.Damn.class));
        cards.add(new SetCardInfo("Daring Saboteur", 151, Rarity.UNCOMMON, mage.cards.d.DaringSaboteur.class));
        cards.add(new SetCardInfo("Deathgorge Scavenger", 236, Rarity.RARE, mage.cards.d.DeathgorgeScavenger.class));
        cards.add(new SetCardInfo("Deeproot Elite", 237, Rarity.RARE, mage.cards.d.DeeprootElite.class));
        cards.add(new SetCardInfo("Deeproot Historian", 91, Rarity.RARE, mage.cards.d.DeeprootHistorian.class));
        cards.add(new SetCardInfo("Deeproot Waters", 152, Rarity.UNCOMMON, mage.cards.d.DeeprootWaters.class));
        cards.add(new SetCardInfo("Departed Deckhand", 153, Rarity.UNCOMMON, mage.cards.d.DepartedDeckhand.class));
        cards.add(new SetCardInfo("Descendants' Path", 238, Rarity.RARE, mage.cards.d.DescendantsPath.class));
        cards.add(new SetCardInfo("Desolate Lighthouse", 327, Rarity.RARE, mage.cards.d.DesolateLighthouse.class));
        cards.add(new SetCardInfo("Dimir Signet", 302, Rarity.UNCOMMON, mage.cards.d.DimirSignet.class));
        cards.add(new SetCardInfo("Dinosaur Egg", 92, Rarity.RARE, mage.cards.d.DinosaurEgg.class));
        cards.add(new SetCardInfo("Dire Fleet Daredevil", 223, Rarity.RARE, mage.cards.d.DireFleetDaredevil.class));
        cards.add(new SetCardInfo("Dire Fleet Ravager", 192, Rarity.MYTHIC, mage.cards.d.DireFleetRavager.class));
        cards.add(new SetCardInfo("Distant Melody", 154, Rarity.COMMON, mage.cards.d.DistantMelody.class));
        cards.add(new SetCardInfo("Don Andres, the Renegade", 6, Rarity.MYTHIC, mage.cards.d.DonAndresTheRenegade.class));
        cards.add(new SetCardInfo("Drana, Liberator of Malakir", 193, Rarity.MYTHIC, mage.cards.d.DranaLiberatorOfMalakir.class));
        cards.add(new SetCardInfo("Drover of the Mighty", 239, Rarity.UNCOMMON, mage.cards.d.DroverOfTheMighty.class));
        cards.add(new SetCardInfo("Dusk Legion Sergeant", 80, Rarity.RARE, mage.cards.d.DuskLegionSergeant.class));
        cards.add(new SetCardInfo("Dusk Legion Zealot", 194, Rarity.COMMON, mage.cards.d.DuskLegionZealot.class));
        cards.add(new SetCardInfo("Elenda's Hierophant", 71, Rarity.RARE, mage.cards.e.ElendasHierophant.class));
        cards.add(new SetCardInfo("Elenda, the Dusk Rose", 268, Rarity.MYTHIC, mage.cards.e.ElendaTheDuskRose.class));
        cards.add(new SetCardInfo("Emperor Mihail II", 155, Rarity.RARE, mage.cards.e.EmperorMihailII.class));
        cards.add(new SetCardInfo("Etali, Primal Storm", 224, Rarity.RARE, mage.cards.e.EtaliPrimalStorm.class));
        cards.add(new SetCardInfo("Etchings of the Chosen", 269, Rarity.UNCOMMON, mage.cards.e.EtchingsOfTheChosen.class));
        cards.add(new SetCardInfo("Evacuation", 156, Rarity.RARE, mage.cards.e.Evacuation.class));
        cards.add(new SetCardInfo("Everflowing Chalice", 111, Rarity.UNCOMMON, mage.cards.e.EverflowingChalice.class));
        cards.add(new SetCardInfo("Evolution Sage", 240, Rarity.UNCOMMON, mage.cards.e.EvolutionSage.class));
        cards.add(new SetCardInfo("Evolving Wilds", 328, Rarity.COMMON, mage.cards.e.EvolvingWilds.class));
        cards.add(new SetCardInfo("Eye of Ojer Taq", 15, Rarity.MYTHIC, mage.cards.e.EyeOfOjerTaq.class));
        cards.add(new SetCardInfo("Exotic Orchard", 329, Rarity.RARE, mage.cards.e.ExoticOrchard.class));
        cards.add(new SetCardInfo("Expedition Map", 112, Rarity.UNCOMMON, mage.cards.e.ExpeditionMap.class));
        cards.add(new SetCardInfo("Explore", 241, Rarity.COMMON, mage.cards.e.Explore.class));
        cards.add(new SetCardInfo("Exquisite Blood", 195, Rarity.RARE, mage.cards.e.ExquisiteBlood.class));
        cards.add(new SetCardInfo("Faithless Looting", 225, Rarity.COMMON, mage.cards.f.FaithlessLooting.class));
        cards.add(new SetCardInfo("Falkenrath Noble", 196, Rarity.UNCOMMON, mage.cards.f.FalkenrathNoble.class));
        cards.add(new SetCardInfo("Farseek", 242, Rarity.COMMON, mage.cards.f.Farseek.class));
        cards.add(new SetCardInfo("Fathom Fleet Captain", 197, Rarity.RARE, mage.cards.f.FathomFleetCaptain.class));
        cards.add(new SetCardInfo("Feed the Swarm", 198, Rarity.COMMON, mage.cards.f.FeedTheSwarm.class));
        cards.add(new SetCardInfo("Fiery Confluence", 226, Rarity.RARE, mage.cards.f.FieryConfluence.class));
        cards.add(new SetCardInfo("Fist of Suns", 113, Rarity.RARE, mage.cards.f.FistOfSuns.class));
        cards.add(new SetCardInfo("Foreboding Ruins", 330, Rarity.RARE, mage.cards.f.ForebodingRuins.class));
        cards.add(new SetCardInfo("Fortified Village", 331, Rarity.RARE, mage.cards.f.FortifiedVillage.class));
        cards.add(new SetCardInfo("Francisco, Fowl Marauder", 49, Rarity.RARE, mage.cards.f.FranciscoFowlMarauder.class));
        cards.add(new SetCardInfo("From the Rubble", 72, Rarity.RARE, mage.cards.f.FromTheRubble.class));
        cards.add(new SetCardInfo("Frostboil Snarl", 332, Rarity.RARE, mage.cards.f.FrostboilSnarl.class));
        cards.add(new SetCardInfo("Furycalm Snarl", 333, Rarity.RARE, mage.cards.f.FurycalmSnarl.class));
        cards.add(new SetCardInfo("Game Trail", 334, Rarity.RARE, mage.cards.g.GameTrail.class));
        cards.add(new SetCardInfo("Geier Reach Sanitarium", 335, Rarity.RARE, mage.cards.g.GeierReachSanitarium.class));
        cards.add(new SetCardInfo("Gemcutter Buccaneer", 55, Rarity.RARE, mage.cards.g.GemcutterBuccaneer.class));
        cards.add(new SetCardInfo("Generous Gift", 128, Rarity.UNCOMMON, mage.cards.g.GenerousGift.class));
        cards.add(new SetCardInfo("Ghost of Ramirez DePietro", 157, Rarity.UNCOMMON, mage.cards.g.GhostOfRamirezDePietro.class));
        cards.add(new SetCardInfo("Glass-Cast Heart", 199, Rarity.RARE, mage.cards.g.GlassCastHeart.class));
        cards.add(new SetCardInfo("Growth Spiral", 270, Rarity.COMMON, mage.cards.g.GrowthSpiral.class));
        cards.add(new SetCardInfo("Hakbal of the Surging Soul", 3, Rarity.MYTHIC, mage.cards.h.HakbalOfTheSurgingSoul.class));
        cards.add(new SetCardInfo("Hardened Scales", 243, Rarity.RARE, mage.cards.h.HardenedScales.class));
        cards.add(new SetCardInfo("Heirloom Blade", 303, Rarity.UNCOMMON, mage.cards.h.HeirloomBlade.class));
        cards.add(new SetCardInfo("Herald of Secret Streams", 158, Rarity.RARE, mage.cards.h.HeraldOfSecretStreams.class));
        cards.add(new SetCardInfo("Herald's Horn", 304, Rarity.UNCOMMON, mage.cards.h.HeraldsHorn.class));
        cards.add(new SetCardInfo("Hinterland Harbor", 336, Rarity.RARE, mage.cards.h.HinterlandHarbor.class));
        cards.add(new SetCardInfo("Hostage Taker", 271, Rarity.RARE, mage.cards.h.HostageTaker.class));
        cards.add(new SetCardInfo("Icon of Ancestry", 305, Rarity.RARE, mage.cards.i.IconOfAncestry.class));
        cards.add(new SetCardInfo("Illustrious Wanderglyph", 9, Rarity.RARE, mage.cards.i.IllustriousWanderglyph.class));
        cards.add(new SetCardInfo("Indulgent Aristocrat", 200, Rarity.UNCOMMON, mage.cards.i.IndulgentAristocrat.class));
        cards.add(new SetCardInfo("Inspiring Call", 244, Rarity.UNCOMMON, mage.cards.i.InspiringCall.class));
        cards.add(new SetCardInfo("Isolated Chapel", 337, Rarity.RARE, mage.cards.i.IsolatedChapel.class));
        cards.add(new SetCardInfo("Izzet Signet", 306, Rarity.UNCOMMON, mage.cards.i.IzzetSignet.class));
        cards.add(new SetCardInfo("Jungle Shrine", 338, Rarity.UNCOMMON, mage.cards.j.JungleShrine.class));
        cards.add(new SetCardInfo("Kari Zev, Skyship Raider", 227, Rarity.RARE, mage.cards.k.KariZevSkyshipRaider.class));
        cards.add(new SetCardInfo("Karn's Bastion", 339, Rarity.RARE, mage.cards.k.KarnsBastion.class));
        cards.add(new SetCardInfo("Kessig Wolf Run", 340, Rarity.RARE, mage.cards.k.KessigWolfRun.class));
        cards.add(new SetCardInfo("Kindred Boon", 129, Rarity.RARE, mage.cards.k.KindredBoon.class));
        cards.add(new SetCardInfo("Kindred Discovery", 159, Rarity.RARE, mage.cards.k.KindredDiscovery.class));
        cards.add(new SetCardInfo("King Narfi's Betrayal", 272, Rarity.RARE, mage.cards.k.KingNarfisBetrayal.class));
        cards.add(new SetCardInfo("Kinjalli's Sunwing", 130, Rarity.RARE, mage.cards.k.KinjallisSunwing.class));
        cards.add(new SetCardInfo("Kiora's Follower", 273, Rarity.UNCOMMON, mage.cards.k.KiorasFollower.class));
        cards.add(new SetCardInfo("Kodama's Reach", 245, Rarity.COMMON, mage.cards.k.KodamasReach.class));
        cards.add(new SetCardInfo("Kopala, Warden of Waves", 160, Rarity.RARE, mage.cards.k.KopalaWardenOfWaves.class));
        cards.add(new SetCardInfo("Kumena, Tyrant of Orazca", 274, Rarity.MYTHIC, mage.cards.k.KumenaTyrantOfOrazca.class));
        cards.add(new SetCardInfo("Legion Lieutenant", 275, Rarity.UNCOMMON, mage.cards.l.LegionLieutenant.class));
        cards.add(new SetCardInfo("Lethal Scheme", 201, Rarity.RARE, mage.cards.l.LethalScheme.class));
        cards.add(new SetCardInfo("Lifecrafter's Bestiary", 307, Rarity.RARE, mage.cards.l.LifecraftersBestiary.class));
        cards.add(new SetCardInfo("Lightning Greaves", 114, Rarity.UNCOMMON, mage.cards.l.LightningGreaves.class));
        cards.add(new SetCardInfo("Llanowar Reborn", 341, Rarity.UNCOMMON, mage.cards.l.LlanowarReborn.class));
        cards.add(new SetCardInfo("Majestic Heliopterus", 131, Rarity.UNCOMMON, mage.cards.m.MajesticHeliopterus.class));
        cards.add(new SetCardInfo("Malcolm, Keen-Eyed Navigator", 161, Rarity.UNCOMMON, mage.cards.m.MalcolmKeenEyedNavigator.class));
        cards.add(new SetCardInfo("Marauding Raptor", 228, Rarity.RARE, mage.cards.m.MaraudingRaptor.class));
        cards.add(new SetCardInfo("March of the Canonized", 73, Rarity.RARE, mage.cards.m.MarchOfTheCanonized.class));
        cards.add(new SetCardInfo("Martyr of Dusk", 132, Rarity.COMMON, mage.cards.m.MartyrOfDusk.class));
        cards.add(new SetCardInfo("Master of Dark Rites", 83, Rarity.RARE, mage.cards.m.MasterOfDarkRites.class));
        cards.add(new SetCardInfo("Master of the Pearl Trident", 162, Rarity.RARE, mage.cards.m.MasterOfThePearlTrident.class));
        cards.add(new SetCardInfo("Mavren Fein, Dusk Apostle", 133, Rarity.RARE, mage.cards.m.MavrenFeinDuskApostle.class));
        cards.add(new SetCardInfo("Merchant Raiders", 163, Rarity.UNCOMMON, mage.cards.m.MerchantRaiders.class));
        cards.add(new SetCardInfo("Merfolk Mistbinder", 276, Rarity.UNCOMMON, mage.cards.m.MerfolkMistbinder.class));
        cards.add(new SetCardInfo("Merfolk Skydiver", 277, Rarity.UNCOMMON, mage.cards.m.MerfolkSkydiver.class));
        cards.add(new SetCardInfo("Merfolk Sovereign", 164, Rarity.RARE, mage.cards.m.MerfolkSovereign.class));
        cards.add(new SetCardInfo("Merrow Reejerey", 165, Rarity.UNCOMMON, mage.cards.m.MerrowReejerey.class));
        cards.add(new SetCardInfo("Metallic Mimic", 308, Rarity.RARE, mage.cards.m.MetallicMimic.class));
        cards.add(new SetCardInfo("Migration Path", 246, Rarity.UNCOMMON, mage.cards.m.MigrationPath.class));
        cards.add(new SetCardInfo("Mimic Vat", 115, Rarity.RARE, mage.cards.m.MimicVat.class));
        cards.add(new SetCardInfo("Mind Stone", 309, Rarity.COMMON, mage.cards.m.MindStone.class));
        cards.add(new SetCardInfo("Mist Dancer", 76, Rarity.RARE, mage.cards.m.MistDancer.class));
        cards.add(new SetCardInfo("Mosswort Bridge", 342, Rarity.RARE, mage.cards.m.MosswortBridge.class));
        cards.add(new SetCardInfo("Myriad Landscape", 343, Rarity.UNCOMMON, mage.cards.m.MyriadLandscape.class));
        cards.add(new SetCardInfo("Nephalia Drownyard", 344, Rarity.RARE, mage.cards.n.NephaliaDrownyard.class));
        cards.add(new SetCardInfo("New Blood", 202, Rarity.RARE, mage.cards.n.NewBlood.class));
        cards.add(new SetCardInfo("Nighthawk Scavenger", 203, Rarity.RARE, mage.cards.n.NighthawkScavenger.class));
        cards.add(new SetCardInfo("Oathsworn Vampire", 204, Rarity.UNCOMMON, mage.cards.o.OathswornVampire.class));
        cards.add(new SetCardInfo("Olivia's Wrath", 205, Rarity.RARE, mage.cards.o.OliviasWrath.class));
        cards.add(new SetCardInfo("Ore-Rich Stalactite", 11, Rarity.RARE, mage.cards.o.OreRichStalactite.class));
        cards.add(new SetCardInfo("Order of Sacred Dusk", 97, Rarity.RARE, mage.cards.o.OrderOfSacredDusk.class));
        cards.add(new SetCardInfo("Orzhov Basilica", 345, Rarity.COMMON, mage.cards.o.OrzhovBasilica.class));
        cards.add(new SetCardInfo("Orzhov Signet", 310, Rarity.UNCOMMON, mage.cards.o.OrzhovSignet.class));
        cards.add(new SetCardInfo("Otepec Huntmaster", 229, Rarity.UNCOMMON, mage.cards.o.OtepecHuntmaster.class));
        cards.add(new SetCardInfo("Pact of the Serpent", 206, Rarity.RARE, mage.cards.p.PactOfTheSerpent.class));
        cards.add(new SetCardInfo("Pantlaza, Sun-Favored", 4, Rarity.MYTHIC, mage.cards.p.PantlazaSunFavored.class));
        cards.add(new SetCardInfo("Path of Ancestry", 346, Rarity.COMMON, mage.cards.p.PathOfAncestry.class));
        cards.add(new SetCardInfo("Path to Exile", 134, Rarity.UNCOMMON, mage.cards.p.PathToExile.class));
        cards.add(new SetCardInfo("Patron of the Vein", 207, Rarity.RARE, mage.cards.p.PatronOfTheVein.class));
        cards.add(new SetCardInfo("Pitiless Plunderer", 208, Rarity.UNCOMMON, mage.cards.p.PitilessPlunderer.class));
        cards.add(new SetCardInfo("Port Razer", 230, Rarity.MYTHIC, mage.cards.p.PortRazer.class));
        cards.add(new SetCardInfo("Port of Karfell", 347, Rarity.UNCOMMON, mage.cards.p.PortOfKarfell.class));
        cards.add(new SetCardInfo("Prime Speaker Zegana", 278, Rarity.MYTHIC, mage.cards.p.PrimeSpeakerZegana.class));
        cards.add(new SetCardInfo("Prismari Command", 279, Rarity.RARE, mage.cards.p.PrismariCommand.class));
        cards.add(new SetCardInfo("Progenitor's Icon", 100, Rarity.RARE, mage.cards.p.ProgenitorsIcon.class));
        cards.add(new SetCardInfo("Promise of Aclazotz", 84, Rarity.RARE, mage.cards.p.PromiseOfAclazotz.class));
        cards.add(new SetCardInfo("Quandrix Command", 280, Rarity.RARE, mage.cards.q.QuandrixCommand.class));
        cards.add(new SetCardInfo("Quartzwood Crasher", 281, Rarity.RARE, mage.cards.q.QuartzwoodCrasher.class));
        cards.add(new SetCardInfo("Radiant Destiny", 135, Rarity.RARE, mage.cards.r.RadiantDestiny.class));
        cards.add(new SetCardInfo("Raging Regisaur", 282, Rarity.UNCOMMON, mage.cards.r.RagingRegisaur.class));
        cards.add(new SetCardInfo("Raging Swordtooth", 283, Rarity.UNCOMMON, mage.cards.r.RagingSwordtooth.class));
        cards.add(new SetCardInfo("Rakdos Charm", 284, Rarity.UNCOMMON, mage.cards.r.RakdosCharm.class));
        cards.add(new SetCardInfo("Rakdos Signet", 311, Rarity.UNCOMMON, mage.cards.r.RakdosSignet.class));
        cards.add(new SetCardInfo("Ramirez DePietro, Pillager", 285, Rarity.UNCOMMON, mage.cards.r.RamirezDePietroPillager.class));
        cards.add(new SetCardInfo("Rampaging Brontodon", 247, Rarity.RARE, mage.cards.r.RampagingBrontodon.class));
        cards.add(new SetCardInfo("Rampant Growth", 248, Rarity.COMMON, mage.cards.r.RampantGrowth.class));
        cards.add(new SetCardInfo("Ranging Raptors", 249, Rarity.UNCOMMON, mage.cards.r.RangingRaptors.class));
        cards.add(new SetCardInfo("Rapid Hybridization", 166, Rarity.UNCOMMON, mage.cards.r.RapidHybridization.class));
        cards.add(new SetCardInfo("Ravenform", 167, Rarity.COMMON, mage.cards.r.Ravenform.class));
        cards.add(new SetCardInfo("Realmwalker", 250, Rarity.RARE, mage.cards.r.Realmwalker.class));
        cards.add(new SetCardInfo("Redemption Choir", 74, Rarity.RARE, mage.cards.r.RedemptionChoir.class));
        cards.add(new SetCardInfo("Reflections of Littjara", 168, Rarity.RARE, mage.cards.r.ReflectionsOfLittjara.class));
        cards.add(new SetCardInfo("Regal Behemoth", 251, Rarity.RARE, mage.cards.r.RegalBehemoth.class));
        cards.add(new SetCardInfo("Regisaur Alpha", 286, Rarity.RARE, mage.cards.r.RegisaurAlpha.class));
        cards.add(new SetCardInfo("Reliquary Tower", 348, Rarity.UNCOMMON, mage.cards.r.ReliquaryTower.class));
        cards.add(new SetCardInfo("Return of the Wildspeaker", 252, Rarity.RARE, mage.cards.r.ReturnOfTheWildspeaker.class));
        cards.add(new SetCardInfo("Return to Dust", 136, Rarity.UNCOMMON, mage.cards.r.ReturnToDust.class));
        cards.add(new SetCardInfo("Rhythm of the Wild", 287, Rarity.UNCOMMON, mage.cards.r.RhythmOfTheWild.class));
        cards.add(new SetCardInfo("Ripjaw Raptor", 253, Rarity.RARE, mage.cards.r.RipjawRaptor.class));
        cards.add(new SetCardInfo("Ripples of Potential", 77, Rarity.RARE, mage.cards.r.RipplesOfPotential.class));
        cards.add(new SetCardInfo("Rishkar's Expertise", 254, Rarity.RARE, mage.cards.r.RishkarsExpertise.class));
        cards.add(new SetCardInfo("Rogue's Passage", 349, Rarity.UNCOMMON, mage.cards.r.RoguesPassage.class));
        cards.add(new SetCardInfo("Ruinous Intrusion", 255, Rarity.RARE, mage.cards.r.RuinousIntrusion.class));
        cards.add(new SetCardInfo("Runic Armasaur", 256, Rarity.RARE, mage.cards.r.RunicArmasaur.class));
        cards.add(new SetCardInfo("Sage of Fables", 169, Rarity.UNCOMMON, mage.cards.s.SageOfFables.class));
        cards.add(new SetCardInfo("Sanctum Seeker", 209, Rarity.RARE, mage.cards.s.SanctumSeeker.class));
        cards.add(new SetCardInfo("Savage Stomp", 257, Rarity.UNCOMMON, mage.cards.s.SavageStomp.class));
        cards.add(new SetCardInfo("Scion of Calamity", 93, Rarity.RARE, mage.cards.s.ScionOfCalamity.class));
        cards.add(new SetCardInfo("Seafloor Oracle", 170, Rarity.RARE, mage.cards.s.SeafloorOracle.class));
        cards.add(new SetCardInfo("Secluded Courtyard", 350, Rarity.UNCOMMON, mage.cards.s.SecludedCourtyard.class));
        cards.add(new SetCardInfo("Shared Animosity", 231, Rarity.RARE, mage.cards.s.SharedAnimosity.class));
        cards.add(new SetCardInfo("Shifting Ceratops", 258, Rarity.RARE, mage.cards.s.ShiftingCeratops.class));
        cards.add(new SetCardInfo("Shineshadow Snarl", 351, Rarity.RARE, mage.cards.s.ShineshadowSnarl.class));
        cards.add(new SetCardInfo("Simic Ascendancy", 288, Rarity.RARE, mage.cards.s.SimicAscendancy.class));
        cards.add(new SetCardInfo("Simic Growth Chamber", 352, Rarity.COMMON, mage.cards.s.SimicGrowthChamber.class));
        cards.add(new SetCardInfo("Simic Signet", 312, Rarity.UNCOMMON, mage.cards.s.SimicSignet.class));
        cards.add(new SetCardInfo("Singer of Swift Rivers", 98, Rarity.RARE, mage.cards.s.SingerOfSwiftRivers.class));
        cards.add(new SetCardInfo("Siren Stormtamer", 171, Rarity.UNCOMMON, mage.cards.s.SirenStormtamer.class));
        cards.add(new SetCardInfo("Skeleton Crew", 85, Rarity.RARE, mage.cards.s.SkeletonCrew.class));
        cards.add(new SetCardInfo("Smoldering Marsh", 353, Rarity.RARE, mage.cards.s.SmolderingMarsh.class));
        cards.add(new SetCardInfo("Sol Ring", 313, Rarity.UNCOMMON, mage.cards.s.SolRing.class));
        cards.add(new SetCardInfo("Sorin, Lord of Innistrad", 289, Rarity.MYTHIC, mage.cards.s.SorinLordOfInnistrad.class));
        cards.add(new SetCardInfo("Spectral Sailor", 172, Rarity.UNCOMMON, mage.cards.s.SpectralSailor.class));
        cards.add(new SetCardInfo("Stonybrook Banneret", 173, Rarity.COMMON, mage.cards.s.StonybrookBanneret.class));
        cards.add(new SetCardInfo("Storm Fleet Negotiator", 78, Rarity.RARE, mage.cards.s.StormFleetNegotiator.class));
        cards.add(new SetCardInfo("Strionic Resonator", 116, Rarity.RARE, mage.cards.s.StrionicResonator.class));
        cards.add(new SetCardInfo("Sulfur Falls", 354, Rarity.RARE, mage.cards.s.SulfurFalls.class));
        cards.add(new SetCardInfo("Sunfrill Imitator", 94, Rarity.RARE, mage.cards.s.SunfrillImitator.class));
        cards.add(new SetCardInfo("Sunken Hollow", 355, Rarity.RARE, mage.cards.s.SunkenHollow.class));
        cards.add(new SetCardInfo("Surgespanner", 174, Rarity.RARE, mage.cards.s.Surgespanner.class));
        cards.add(new SetCardInfo("Svyelun of Sea and Sky", 175, Rarity.MYTHIC, mage.cards.s.SvyelunOfSeaAndSky.class));
        cards.add(new SetCardInfo("Swiftfoot Boots", 314, Rarity.UNCOMMON, mage.cards.s.SwiftfootBoots.class));
        cards.add(new SetCardInfo("Swords to Plowshares", 137, Rarity.UNCOMMON, mage.cards.s.SwordsToPlowshares.class));
        cards.add(new SetCardInfo("Tainted Field", 356, Rarity.UNCOMMON, mage.cards.t.TaintedField.class));
        cards.add(new SetCardInfo("Talisman of Hierarchy", 315, Rarity.UNCOMMON, mage.cards.t.TalismanOfHierarchy.class));
        cards.add(new SetCardInfo("Tatyova, Benthic Druid", 290, Rarity.UNCOMMON, mage.cards.t.TatyovaBenthicDruid.class));
        cards.add(new SetCardInfo("Temple Altisaur", 138, Rarity.RARE, mage.cards.t.TempleAltisaur.class));
        cards.add(new SetCardInfo("Temple Bell", 117, Rarity.RARE, mage.cards.t.TempleBell.class));
        cards.add(new SetCardInfo("Temple of Mystery", 357, Rarity.RARE, mage.cards.t.TempleOfMystery.class));
        cards.add(new SetCardInfo("Temple of Silence", 358, Rarity.RARE, mage.cards.t.TempleOfSilence.class));
        cards.add(new SetCardInfo("Temple of the False God", 359, Rarity.UNCOMMON, mage.cards.t.TempleOfTheFalseGod.class));
        cards.add(new SetCardInfo("Terramorphic Expanse", 360, Rarity.COMMON, mage.cards.t.TerramorphicExpanse.class));
        cards.add(new SetCardInfo("Tetzin, Gnome Champion", 13, Rarity.RARE, mage.cards.t.TetzinGnomeChampion.class));
        cards.add(new SetCardInfo("Thassa, God of the Sea", 176, Rarity.MYTHIC, mage.cards.t.ThassaGodOfTheSea.class));
        cards.add(new SetCardInfo("The Golden-Gear Colossus", 13, Rarity.RARE, mage.cards.t.TheGoldenGearColossus.class));
        cards.add(new SetCardInfo("The Indomitable", 43, Rarity.RARE, mage.cards.t.TheIndomitable.class, NON_FULL_USE_VARIOUS));
        cards.add(new SetCardInfo("The Indomitable", 75, Rarity.RARE, mage.cards.t.TheIndomitable.class, NON_FULL_USE_VARIOUS));
        cards.add(new SetCardInfo("The Grim Captain's Locker", 82, Rarity.RARE, mage.cards.t.TheGrimCaptainsLocker.class));
        cards.add(new SetCardInfo("Thieving Skydiver", 177, Rarity.RARE, mage.cards.t.ThievingSkydiver.class));
        cards.add(new SetCardInfo("Thought Vessel", 118, Rarity.UNCOMMON, mage.cards.t.ThoughtVessel.class));
        cards.add(new SetCardInfo("Thriving Bluff", 361, Rarity.COMMON, mage.cards.t.ThrivingBluff.class));
        cards.add(new SetCardInfo("Thriving Grove", 362, Rarity.COMMON, mage.cards.t.ThrivingGrove.class));
        cards.add(new SetCardInfo("Thriving Heath", 363, Rarity.COMMON, mage.cards.t.ThrivingHeath.class));
        cards.add(new SetCardInfo("Thriving Isle", 364, Rarity.COMMON, mage.cards.t.ThrivingIsle.class));
        cards.add(new SetCardInfo("Thriving Moor", 365, Rarity.COMMON, mage.cards.t.ThrivingMoor.class));
        cards.add(new SetCardInfo("Thunderherd Migration", 259, Rarity.UNCOMMON, mage.cards.t.ThunderherdMigration.class));
        cards.add(new SetCardInfo("Thundering Spineback", 260, Rarity.UNCOMMON, mage.cards.t.ThunderingSpineback.class));
        cards.add(new SetCardInfo("Timestream Navigator", 178, Rarity.MYTHIC, mage.cards.t.TimestreamNavigator.class));
        cards.add(new SetCardInfo("Timothar, Baron of Bats", 210, Rarity.MYTHIC, mage.cards.t.TimotharBaronOfBats.class));
        cards.add(new SetCardInfo("Tishana, Voice of Thunder", 291, Rarity.MYTHIC, mage.cards.t.TishanaVoiceOfThunder.class));
        cards.add(new SetCardInfo("Topiary Stomper", 261, Rarity.RARE, mage.cards.t.TopiaryStomper.class));
        cards.add(new SetCardInfo("Topography Tracker", 95, Rarity.RARE, mage.cards.t.TopographyTracker.class));
        cards.add(new SetCardInfo("Tributary Instructor", 96, Rarity.RARE, mage.cards.t.TributaryInstructor.class));
        cards.add(new SetCardInfo("Twilight Prophet", 211, Rarity.MYTHIC, mage.cards.t.TwilightProphet.class));
        cards.add(new SetCardInfo("Unclaimed Territory", 366, Rarity.UNCOMMON, mage.cards.u.UnclaimedTerritory.class));
        cards.add(new SetCardInfo("Utter End", 292, Rarity.RARE, mage.cards.u.UtterEnd.class));
        cards.add(new SetCardInfo("Vanquisher's Banner", 316, Rarity.RARE, mage.cards.v.VanquishersBanner.class));
        cards.add(new SetCardInfo("Vault of the Archangel", 367, Rarity.RARE, mage.cards.v.VaultOfTheArchangel.class));
        cards.add(new SetCardInfo("Verdant Sun's Avatar", 262, Rarity.RARE, mage.cards.v.VerdantSunsAvatar.class));
        cards.add(new SetCardInfo("Village Rites", 212, Rarity.COMMON, mage.cards.v.VillageRites.class));
        cards.add(new SetCardInfo("Vineglimmer Snarl", 368, Rarity.RARE, mage.cards.v.VineglimmerSnarl.class));
        cards.add(new SetCardInfo("Viscera Seer", 213, Rarity.COMMON, mage.cards.v.VisceraSeer.class));
        cards.add(new SetCardInfo("Voldaren Estate", 369, Rarity.RARE, mage.cards.v.VoldarenEstate.class));
        cards.add(new SetCardInfo("Vona, Butcher of Magan", 293, Rarity.MYTHIC, mage.cards.v.VonaButcherOfMagan.class));
        cards.add(new SetCardInfo("Vorel of the Hull Clade", 294, Rarity.RARE, mage.cards.v.VorelOfTheHullClade.class));
        cards.add(new SetCardInfo("Wakening Sun's Avatar", 139, Rarity.MYTHIC, mage.cards.w.WakeningSunsAvatar.class));
        cards.add(new SetCardInfo("Warkite Marauder", 179, Rarity.RARE, mage.cards.w.WarkiteMarauder.class));
        cards.add(new SetCardInfo("Wave Goodbye", 79, Rarity.RARE, mage.cards.w.WaveGoodbye.class));
        cards.add(new SetCardInfo("Wayfarer's Bauble", 317, Rarity.COMMON, mage.cards.w.WayfarersBauble.class));
        cards.add(new SetCardInfo("Wayta, Trainer Prodigy", 7, Rarity.MYTHIC, mage.cards.w.WaytaTrainerProdigy.class));
        cards.add(new SetCardInfo("Wayward Swordtooth", 263, Rarity.RARE, mage.cards.w.WaywardSwordtooth.class));
        cards.add(new SetCardInfo("Wedding Ring", 102, Rarity.MYTHIC, mage.cards.w.WeddingRing.class));
        cards.add(new SetCardInfo("Welcoming Vampire", 140, Rarity.RARE, mage.cards.w.WelcomingVampire.class));
        cards.add(new SetCardInfo("Whispersilk Cloak", 119, Rarity.UNCOMMON, mage.cards.w.WhispersilkCloak.class));
        cards.add(new SetCardInfo("Windbrisk Heights", 370, Rarity.RARE, mage.cards.w.WindbriskHeights.class));
        cards.add(new SetCardInfo("Windfall", 180, Rarity.UNCOMMON, mage.cards.w.Windfall.class));
        cards.add(new SetCardInfo("Worn Powerstone", 120, Rarity.UNCOMMON, mage.cards.w.WornPowerstone.class));
        cards.add(new SetCardInfo("Wrathful Raptors", 88, Rarity.RARE, mage.cards.w.WrathfulRaptors.class));
        cards.add(new SetCardInfo("Wretched Bonemass", 10, Rarity.RARE, mage.cards.w.WretchedBonemass.class));
        cards.add(new SetCardInfo("Xavier Sal, Infested Captain", 14, Rarity.RARE, mage.cards.x.XavierSalInfestedCaptain.class));
        cards.add(new SetCardInfo("Xenagos, God of Revels", 295, Rarity.MYTHIC, mage.cards.x.XenagosGodOfRevels.class));
        cards.add(new SetCardInfo("Xolatoyac, the Smiling Flood", 8, Rarity.MYTHIC, mage.cards.x.XolatoyacTheSmilingFlood.class));
        cards.add(new SetCardInfo("Yahenni, Undying Partisan", 214, Rarity.RARE, mage.cards.y.YahenniUndyingPartisan.class));
        cards.add(new SetCardInfo("Zacama, Primal Calamity", 296, Rarity.MYTHIC, mage.cards.z.ZacamaPrimalCalamity.class));
        cards.add(new SetCardInfo("Zara, Renegade Recruiter", 297, Rarity.RARE, mage.cards.z.ZaraRenegadeRecruiter.class));
        cards.add(new SetCardInfo("Zegana, Utopian Speaker", 298, Rarity.RARE, mage.cards.z.ZeganaUtopianSpeaker.class));
        cards.add(new SetCardInfo("Zetalpa, Primal Dawn", 141, Rarity.RARE, mage.cards.z.ZetalpaPrimalDawn.class));
    }
}
