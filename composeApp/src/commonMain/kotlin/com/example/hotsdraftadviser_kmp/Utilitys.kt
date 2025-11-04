package com.example.hotsdraftadviser_kmp

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotsdraftadviser_kmp.enums.Difficulty
import com.example.hotsdraftadviser_kmp.enums.GameOrigin
import com.example.hotsdraftadviser_kmp.enums.PlatformType
import com.example.hotsdraftadviser_kmp.enums.RoleEnum
import hotsdraftadviser_kmp.composeapp.generated.resources.Res
import hotsdraftadviser_kmp.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource

object Utilitys {
    fun mapDifficultyForChamp(champName: String): Difficulty? {
        when (champName) {
            "Abathur" -> return Difficulty.EXTREME
            "Alarak" -> return Difficulty.EXTREME
            "Alexstrasza" -> return Difficulty.MEDIUM
            "Ana" -> return Difficulty.EXTREME
            "Anduin" -> return Difficulty.MEDIUM
            "Anubarak" -> return Difficulty.HARD
            "Artanis" -> return Difficulty.HARD
            "Arthas" -> return Difficulty.MEDIUM
            "Auriel" -> return Difficulty.HARD
            "Azmodan" -> return Difficulty.MEDIUM
            "Blaze" -> return Difficulty.MEDIUM
            "Brightwing" -> return Difficulty.EASY
            "Cassia" -> return Difficulty.MEDIUM
            "Chen" -> return Difficulty.MEDIUM
            "Cho" -> return Difficulty.MEDIUM
            "Chogall" -> return Difficulty.MEDIUM
            "Chromie" -> return Difficulty.HARD
            "Deathwing" -> return Difficulty.HARD
            "Deckard" -> return Difficulty.MEDIUM
            "Dehaka" -> return Difficulty.MEDIUM
            "Diablo" -> return Difficulty.HARD
            "DVA" -> return Difficulty.MEDIUM
            "ETC" -> return Difficulty.HARD
            "Falstad" -> return Difficulty.MEDIUM
            "Fenix" -> return Difficulty.MEDIUM
            "Gall" -> return Difficulty.EASY
            "Garrosh" -> return Difficulty.HARD
            "Gazlowe" -> return Difficulty.MEDIUM
            "Genji" -> return Difficulty.EXTREME
            "Greymane" -> return Difficulty.HARD
            "Guldan" -> return Difficulty.MEDIUM
            "Hanzo" -> return Difficulty.HARD
            "Hogger" -> return Difficulty.HARD
            "Illidan" -> return Difficulty.EXTREME
            "Imperius" -> return Difficulty.MEDIUM
            "Jaina" -> return Difficulty.MEDIUM
            "Johanna" -> return Difficulty.EASY
            "Junkrat" -> return Difficulty.HARD
            "Kaelthas" -> return Difficulty.HARD
            "Kelthuzad" -> return Difficulty.EXTREME
            "Kerrigan" -> return Difficulty.EXTREME
            "Kharazim" -> return Difficulty.HARD
            "Leoric" -> return Difficulty.MEDIUM
            "Lili" -> return Difficulty.EASY
            "Li-Ming" -> return Difficulty.MEDIUM
            "LtMorales" -> return Difficulty.EXTREME
            "Lucio" -> return Difficulty.MEDIUM
            "Lunara" -> return Difficulty.MEDIUM
            "Maiev" -> return Difficulty.EXTREME
            "Malfurion" -> return Difficulty.HARD
            "Malganis" -> return Difficulty.HARD
            "Malthael" -> return Difficulty.HARD
            "Medivh" -> return Difficulty.EXTREME
            "Mei" -> return Difficulty.HARD
            "Mephisto" -> return Difficulty.HARD
            "Muradin" -> return Difficulty.EASY
            "Murky" -> return Difficulty.HARD
            "Nazeebo" -> return Difficulty.MEDIUM
            "Nova" -> return Difficulty.HARD
            "Orphea" -> return Difficulty.HARD
            "Probius" -> return Difficulty.HARD
            "Qhira" -> return Difficulty.HARD
            "Ragnaros" -> return Difficulty.MEDIUM
            "Raynor" -> return Difficulty.EASY
            "Rehgar" -> return Difficulty.MEDIUM
            "Rexxar" -> return Difficulty.EXTREME
            "Samuro" -> return Difficulty.EXTREME
            "SgtHammer" -> return Difficulty.HARD
            "Sonya" -> return Difficulty.MEDIUM
            "Stitches" -> return Difficulty.HARD
            "Stukov" -> return Difficulty.HARD
            "Sylvanas" -> return Difficulty.HARD
            "Tassadar" -> return Difficulty.HARD
            "Butcher" -> return Difficulty.HARD
            "LostVikings" -> return Difficulty.EXTREME
            "Thrall" -> return Difficulty.MEDIUM
            "Tracer" -> return Difficulty.HARD
            "Tychus" -> return Difficulty.HARD
            "Tyrael" -> return Difficulty.HARD
            "Tyrande" -> return Difficulty.HARD
            "Uther" -> return Difficulty.HARD
            "Valeera" -> return Difficulty.EXTREME
            "Valla" -> return Difficulty.MEDIUM
            "Varian" -> return Difficulty.MEDIUM
            "Whitemane" -> return Difficulty.EXTREME
            "Xul" -> return Difficulty.MEDIUM
            "Yrel" -> return Difficulty.HARD
            "Zagara" -> return Difficulty.MEDIUM
            "Zeratul" -> return Difficulty.EXTREME
            "Zarya" -> return Difficulty.MEDIUM
            "Zuljin" -> return Difficulty.MEDIUM


            else -> return null
        }
    }


    fun mapMapNameToDrawable(champName: String): DrawableResource? {
        when (champName) {
            "Alterac Pass" -> return Res.drawable.map_alteracpass_card
            "Battlefield of Eternity" -> return Res.drawable.map_battlefield_of_eternity_card
            "Black Hearts Bay" -> return Res.drawable.map_black_hearts_bay_card
            "Braxis Holdout" -> return Res.drawable.map_braxis_holdout_card
            "Cursed Hollow" -> return Res.drawable.map_cursed_hollow_card
            "Dragonshire" -> return Res.drawable.map_dragon_shire_card
            "Garden of Terror" -> return Res.drawable.map_garden_of_terror_card
            "Hanamura" -> return Res.drawable.map_hanamura_card
            "Haunted Mines" -> return Res.drawable.map_haunted_mines_card
            "Infernal Shrines" -> return Res.drawable.map_infernal_shrines_card
            "Lost Caverns" -> return Res.drawable.map_lost_caverns_card
            "Sky Temple" -> return Res.drawable.map_sky_temple_card
            "Tomb of the Spider Queen" -> return Res.drawable.map_tomb_of_the_spider_queen_card
            "Towers of Doom" -> return Res.drawable.map_towers_of_doom_card
            "Volskaya Foundry" -> return Res.drawable.map_volskaya_foundry_card
            "Warhead Junction" -> return Res.drawable.map_warhead_junction_card
            //TODO Fix
            else -> return Res.drawable.map_empty
        }
    }

    /*
    fun mapMapNameToStringRessource(mapName: String): StringResource? {
        when (mapName) {
            "Alterac Pass" -> return Res.string.map_name_Alterac_Pass
            "Battlefield of Eternity" -> return Res.string.map_name_Battlefield_of_Eternity
            "Black Hearts Bay" -> return Res.string.map_name_Black_Hearts_Bay
            "Braxis Holdout" -> return Res.string.map_name_Braxis_Holdout
            "Cursed Hollow" -> return Res.string.map_name_Cursed_Hollow
            "Dragonshire" -> return Res.string.map_name_Dragonshire
            "Garden of Terror" -> return Res.string.map_name_Garden_of_Terror
            "Hanamura" -> return Res.string.map_name_Hanamura
            "Haunted Mines" -> return Res.string.map_name_Haunted_Mines
            "Infernal Shrines" -> return Res.string.map_name_Infernal_Shrines
            "Lost Caverns" -> return Res.string.map_name_Lost_Caverns
            "Sky Temple" -> return Res.string.map_name_Sky_Temple
            "Tomb of the Spider Queen" -> return Res.string.map_name_Tomb_of_the_Spider_Queen
            "Towers of Doom" -> return Res.string.map_name_Towers_of_Doom
            "Volskaya Foundry" -> return Res.string.map_name_Volskaya_Foundry
            "Warhead Junction" -> return Res.string.map_name_Warhead_Junction

            //TODO FIX
            else -> return Res.string.empty_String
        }
    }

     */

    fun mapChampNameToDrawable(champName: String): DrawableResource? {
        when (champName) {
            "Abathur" -> return Res.drawable.abathur_card_portrait
            "Alarak" -> return Res.drawable.alarak_card_portrait
            "Alexstrasza" -> return Res.drawable.alexstrasza_card_portrait
            "Ana" -> return Res.drawable.ana_card_portrait
            "Anduin" -> return Res.drawable.anduin_card_portrait
            "Anubarak" -> return Res.drawable.anubarak_card_portrait
            "Artanis" -> return Res.drawable.artanis_card_portrait
            "Arthas" -> return Res.drawable.arthas_card_portrait
            "Auriel" -> return Res.drawable.auriel_card_portrait
            "Azmodan" -> return Res.drawable.azmodan_card_portrait
            "Blaze" -> return Res.drawable.blaze_card_portrait
            "Brightwing" -> return Res.drawable.brightwing_card_portrait
            "Cassia" -> return Res.drawable.cassia_card_portrait
            "Chen" -> return Res.drawable.chen_card_portrait
            "Cho" -> return Res.drawable.cho_card_portrait
            "Chogall" -> return Res.drawable.cho_card_portrait
            "Chromie" -> return Res.drawable.chromie_card_portrait
            "Deathwing" -> return Res.drawable.deathwing_card_portrait
            "Deckard" -> return Res.drawable.deckard_card_portrait
            "Dehaka" -> return Res.drawable.dehaka_card_portrait
            "Diablo" -> return Res.drawable.diablo_card_portrait
            "DVA" -> return Res.drawable.dva_card_portrait
            "ETC" -> return Res.drawable.etc_card_portrait
            "Falstad" -> return Res.drawable.falstad_card_portrait
            "Fenix" -> return Res.drawable.fenix_card_portrait
            "Gall" -> return Res.drawable.gall_card_portrait
            "Garrosh" -> return Res.drawable.garrosh_card_portrait
            "Gazlowe" -> return Res.drawable.gazlowe_card_portrait
            "Genji" -> return Res.drawable.genji_card_portrait
            "Greymane" -> return Res.drawable.greymane_card_portrait
            "Guldan" -> return Res.drawable.guldan_card_portrait
            "Hanzo" -> return Res.drawable.hanzo_card_portrait
            "Hogger" -> return Res.drawable.hogger_card_portrait
            "Illidan" -> return Res.drawable.zillidan_card_portrait
            "Imperius" -> return Res.drawable.zimperius_card_portrait
            "Jaina" -> return Res.drawable.jaina_card_portrait
            "Johanna" -> return Res.drawable.johanna_card_portrait
            "Junkrat" -> return Res.drawable.junkrat_card_portrait
            "Kaelthas" -> return Res.drawable.kaelthas_card_portrait
            "Kelthuzad" -> return Res.drawable.kelthuzad_card_portrait
            "Kerrigan" -> return Res.drawable.kerrigan_card_portrait
            "Kharazim" -> return Res.drawable.kharazim_card_portrait
            "Leoric" -> return Res.drawable.leoric_card_portrait
            "Lili" -> return Res.drawable.lili_card_portrait
            "Li-Ming" -> return Res.drawable.liming_card_portrait
            "LtMorales" -> return Res.drawable.ltmorales_card_portrait
            "Lucio" -> return Res.drawable.lucio_card_portrait
            "Lunara" -> return Res.drawable.lunara_card_portrait
            "Maiev" -> return Res.drawable.maiev_card_portrait
            "Malfurion" -> return Res.drawable.malfurion_card_portrait
            "Malganis" -> return Res.drawable.malganis_card_portrait
            "Malthael" -> return Res.drawable.malthael_card_portrait
            "Medivh" -> return Res.drawable.medivh_card_portrait
            "Mei" -> return Res.drawable.mei_card_portrait
            "Mephisto" -> return Res.drawable.mephisto_card_portrait
            "Muradin" -> return Res.drawable.muradin_card_portrait
            "Murky" -> return Res.drawable.murky_card_portrait
            "Nazeebo" -> return Res.drawable.nazeebo_card_portrait
            "Nova" -> return Res.drawable.nova_card_portrait
            "Orphea" -> return Res.drawable.orphea_card_portrait
            "Probius" -> return Res.drawable.probius_card_portrait
            "Qhira" -> return Res.drawable.qhira_card_portrait
            "Ragnaros" -> return Res.drawable.ragnaros_card_portrait
            "Raynor" -> return Res.drawable.raynor_card_portrait
            "Rehgar" -> return Res.drawable.rehgar_card_portrait
            "Rexxar" -> return Res.drawable.rexxar_card_portrait
            "Samuro" -> return Res.drawable.samuro_card_portrait
            "SgtHammer" -> return Res.drawable.sgthammer_card_portrait
            "Sonya" -> return Res.drawable.sonya_card_portrait
            "Stitches" -> return Res.drawable.stitches_card_portrait
            "Stukov" -> return Res.drawable.stukov_card_portrait
            "Sylvanas" -> return Res.drawable.sylvanas_card_portrait
            "Tassadar" -> return Res.drawable.tassadar_card_portrait
            "Butcher" -> return Res.drawable.thebutcher_card_portrait
            "LostVikings" -> return Res.drawable.thelostvikings_card_portrait
            "Thrall" -> return Res.drawable.thrall_card_portrait
            "Tracer" -> return Res.drawable.tracer_card_portrait
            "Tychus" -> return Res.drawable.tychus_card_portrait
            "Tyrael" -> return Res.drawable.tyrael_card_portrait
            "Tyrande" -> return Res.drawable.tyrande_card_portrait
            "Uther" -> return Res.drawable.uther_card_portrait
            "Valeera" -> return Res.drawable.valeera_card_portrait
            "Valla" -> return Res.drawable.valla_card_portrait
            "Varian" -> return Res.drawable.varian_card_portrait
            "Whitemane" -> return Res.drawable.whitemane_card_portrait
            "Xul" -> return Res.drawable.xul_card_portrait
            "Yrel" -> return Res.drawable.yrel_card_portrait
            "Zagara" -> return Res.drawable.zagara_card_portrait
            "Zeratul" -> return Res.drawable.zeratul_card_portrait
            "Zarya" -> return Res.drawable.zarya_card_portrait
            "Zuljin" -> return Res.drawable.zuljin_card_portrait


            else -> return null
        }
    }

    /*
    fun mapChampNameToStringRessource(champName: String): StringResource? {
        when (champName) {
            "Abathur" -> return Res.string.champ_name_abathur
            "Alarak" -> return Res.string.champ_name_Alarak
            "Alexstrasza" -> return Res.string.champ_name_Alexstrasza
            "Ana" -> return Res.string.champ_name_Ana
            "Anduin" -> return Res.string.champ_name_Anduin
            "Anubarak" -> return Res.string.champ_name_Anubarak
            "Artanis" -> return Res.string.champ_name_Artanis
            "Arthas" -> return Res.string.champ_name_Arthas
            "Auriel" -> return Res.string.champ_name_Auriel
            "Azmodan" -> return Res.string.champ_name_Azmodan
            "Blaze" -> return Res.string.champ_name_Blaze
            "Brightwing" -> return Res.string.champ_name_Brightwing
            "Cassia" -> return Res.string.champ_name_Cassia
            "Chen" -> return Res.string.champ_name_Chen
            "Cho" -> return Res.string.champ_name_Cho
            "Chogall" -> return Res.string.champ_name_Chogall
            "Chromie" -> return Res.string.champ_name_Chromie
            "Deathwing" -> return Res.string.champ_name_Deathwing
            "Deckard" -> return Res.string.champ_name_Deckard
            "Dehaka" -> return Res.string.champ_name_Dehaka
            "Diablo" -> return Res.string.champ_name_Diablo
            "DVA" -> return Res.string.champ_name_DVA
            "ETC" -> return Res.string.champ_name_ETC
            "Falstad" -> return Res.string.champ_name_Falstad
            "Fenix" -> return Res.string.champ_name_Fenix
            "Gall" -> return Res.string.champ_name_Gall
            "Garrosh" -> return Res.string.champ_name_Garrosh
            "Gazlowe" -> return Res.string.champ_name_Gazlowe
            "Genji" -> return Res.string.champ_name_Genji
            "Greymane" -> return Res.string.champ_name_Greymane
            "Guldan" -> return Res.string.champ_name_Guldan
            "Hanzo" -> return Res.string.champ_name_Hanzo
            "Hogger" -> return Res.string.champ_name_Hogger
            "Illidan" -> return Res.string.champ_name_Illidan
            "Imperius" -> return Res.string.champ_name_Imperius
            "Jaina" -> return Res.string.champ_name_Jaina
            "Johanna" -> return Res.string.champ_name_Johanna
            "Junkrat" -> return Res.string.champ_name_Junkrat
            "Kaelthas" -> return Res.string.champ_name_Kaelthas
            "Kelthuzad" -> return Res.string.champ_name_Kelthuzad
            "Kerrigan" -> return Res.string.champ_name_Kerrigan
            "Kharazim" -> return Res.string.champ_name_Kharazim
            "Leoric" -> return Res.string.champ_name_Leoric
            "Lili" -> return Res.string.champ_name_Lili
            "Li-Ming" -> return Res.string.champ_name_Li_Ming
            "LtMorales" -> return Res.string.champ_name_LtMorales
            "Lucio" -> return Res.string.champ_name_Lucio
            "Lunara" -> return Res.string.champ_name_Lunara
            "Maiev" -> return Res.string.champ_name_Maiev
            "Malfurion" -> return Res.string.champ_name_Malfurion
            "Malganis" -> return Res.string.champ_name_Malganis
            "Malthael" -> return Res.string.champ_name_Malthael
            "Medivh" -> return Res.string.champ_name_Medivh
            "Mei" -> return Res.string.champ_name_Mei
            "Mephisto" -> return Res.string.champ_name_Mephisto
            "Muradin" -> return Res.string.champ_name_Muradin
            "Murky" -> return Res.string.champ_name_Murky
            "Nazeebo" -> return Res.string.champ_name_Nazeebo
            "Nova" -> return Res.string.champ_name_Nova
            "Orphea" -> return Res.string.champ_name_Orphea
            "Probius" -> return Res.string.champ_name_Probius
            "Qhira" -> return Res.string.champ_name_Qhira
            "Ragnaros" -> return Res.string.champ_name_Ragnaros
            "Raynor" -> return Res.string.champ_name_Raynor
            "Rehgar" -> return Res.string.champ_name_Rehgar
            "Rexxar" -> return Res.string.champ_name_Rexxar
            "Samuro" -> return Res.string.champ_name_Samuro
            "SgtHammer" -> return Res.string.champ_name_SgtHammer
            "Sonya" -> return Res.string.champ_name_Sonya
            "Stitches" -> return Res.string.champ_name_Stitches
            "Stukov" -> return Res.string.champ_name_Stukov
            "Sylvanas" -> return Res.string.champ_name_Sylvanas
            "Tassadar" -> return Res.string.champ_name_Tassadar
            "Butcher" -> return Res.string.champ_name_Butcher
            "LostVikings" -> return Res.string.champ_name_LostVikings
            "Thrall" -> return Res.string.champ_name_Thrall
            "Tracer" -> return Res.string.champ_name_Tracer
            "Tychus" -> return Res.string.champ_name_Tychus
            "Tyrael" -> return Res.string.champ_name_Tyrael
            "Tyrande" -> return Res.string.champ_name_Tyrande
            "Uther" -> return Res.string.champ_name_Uther
            "Valeera" -> return Res.string.champ_name_Valeera
            "Valla" -> return Res.string.champ_name_Valla
            "Varian" -> return Res.string.champ_name_Varian
            "Whitemane" -> return Res.string.champ_name_Whitemane
            "Xul" -> return Res.string.champ_name_Xul
            "Yrel" -> return Res.string.champ_name_Yrel
            "Zagara" -> return Res.string.champ_name_Zagara
            "Zeratul" -> return Res.string.champ_name_Zeratul
            "Zarya" -> return Res.string.champ_name_Zarya
            "Zuljin" -> return Res.string.champ_name_Zuljin


            else -> return null
        }
    }
    */

    fun mapChampToOrigin(champName: String): GameOrigin? {
        when (champName) {
            "Abathur" -> return GameOrigin.STARCRAFT
            "Alarak" -> return GameOrigin.STARCRAFT
            "Alexstrasza" -> return GameOrigin.WARCRAFT
            "Ana" -> return GameOrigin.OVERWATCH
            "Anduin" -> return GameOrigin.WARCRAFT
            "Anubarak" -> return GameOrigin.WARCRAFT
            "Artanis" -> return GameOrigin.STARCRAFT
            "Arthas" -> return GameOrigin.WARCRAFT
            "Auriel" -> return GameOrigin.DIABLO
            "Azmodan" -> return GameOrigin.DIABLO
            "Blaze" -> return GameOrigin.STARCRAFT
            "Brightwing" -> return GameOrigin.WARCRAFT
            "Cassia" -> return GameOrigin.DIABLO
            "Chen" -> return GameOrigin.WARCRAFT
            "Cho" -> return GameOrigin.WARCRAFT
            "Chogall" -> return GameOrigin.WARCRAFT
            "Chromie" -> return GameOrigin.WARCRAFT
            "Deathwing" -> return GameOrigin.WARCRAFT
            "Deckard" -> return GameOrigin.DIABLO
            "Dehaka" -> return GameOrigin.STARCRAFT
            "Diablo" -> return GameOrigin.DIABLO
            "DVA" -> return GameOrigin.OVERWATCH
            "ETC" -> return GameOrigin.WARCRAFT
            "Falstad" -> return GameOrigin.WARCRAFT
            "Fenix" -> return GameOrigin.STARCRAFT
            "Gall" -> return GameOrigin.WARCRAFT
            "Garrosh" -> return GameOrigin.WARCRAFT
            "Gazlowe" -> return GameOrigin.WARCRAFT
            "Genji" -> return GameOrigin.OVERWATCH
            "Greymane" -> return GameOrigin.WARCRAFT
            "Guldan" -> return GameOrigin.WARCRAFT
            "Hanzo" -> return GameOrigin.OVERWATCH
            "Hogger" -> return GameOrigin.WARCRAFT
            "Illidan" -> return GameOrigin.WARCRAFT
            "Imperius" -> return GameOrigin.DIABLO
            "Jaina" -> return GameOrigin.WARCRAFT
            "Johanna" -> return GameOrigin.DIABLO
            "Junkrat" -> return GameOrigin.OVERWATCH
            "Kaelthas" -> return GameOrigin.WARCRAFT
            "Kelthuzad" -> return GameOrigin.WARCRAFT
            "Kerrigan" -> return GameOrigin.STARCRAFT
            "Kharazim" -> return GameOrigin.DIABLO
            "Leoric" -> return GameOrigin.DIABLO
            "Lili" -> return GameOrigin.WARCRAFT
            "Li-Ming" -> return GameOrigin.DIABLO
            "LtMorales" -> return GameOrigin.STARCRAFT
            "Lucio" -> return GameOrigin.OVERWATCH
            "Lunara" -> return GameOrigin.WARCRAFT
            "Maiev" -> return GameOrigin.WARCRAFT
            "Malfurion" -> return GameOrigin.WARCRAFT
            "Malganis" -> return GameOrigin.WARCRAFT
            "Malthael" -> return GameOrigin.DIABLO
            "Medivh" -> return GameOrigin.WARCRAFT
            "Mei" -> return GameOrigin.OVERWATCH
            "Mephisto" -> return GameOrigin.DIABLO
            "Muradin" -> return GameOrigin.WARCRAFT
            "Murky" -> return GameOrigin.WARCRAFT
            "Nazeebo" -> return GameOrigin.DIABLO
            "Nova" -> return GameOrigin.STARCRAFT
            "Orphea" -> return GameOrigin.NEXUS
            "Probius" -> return GameOrigin.STARCRAFT
            "Qhira" -> return GameOrigin.NEXUS
            "Ragnaros" -> return GameOrigin.WARCRAFT
            "Raynor" -> return GameOrigin.STARCRAFT
            "Rehgar" -> return GameOrigin.WARCRAFT
            "Rexxar" -> return GameOrigin.WARCRAFT
            "Samuro" -> return GameOrigin.WARCRAFT
            "SgtHammer" -> return GameOrigin.STARCRAFT
            "Sonya" -> return GameOrigin.DIABLO
            "Stitches" -> return GameOrigin.WARCRAFT
            "Stukov" -> return GameOrigin.STARCRAFT
            "Sylvanas" -> return GameOrigin.WARCRAFT
            "Tassadar" -> return GameOrigin.STARCRAFT
            "Butcher" -> return GameOrigin.DIABLO
            "LostVikings" -> return GameOrigin.NEXUS
            "Thrall" -> return GameOrigin.WARCRAFT
            "Tracer" -> return GameOrigin.OVERWATCH
            "Tychus" -> return GameOrigin.STARCRAFT
            "Tyrael" -> return GameOrigin.DIABLO
            "Tyrande" -> return GameOrigin.WARCRAFT
            "Uther" -> return GameOrigin.WARCRAFT
            "Valeera" -> return GameOrigin.WARCRAFT
            "Valla" -> return GameOrigin.DIABLO
            "Varian" -> return GameOrigin.WARCRAFT
            "Whitemane" -> return GameOrigin.WARCRAFT
            "Xul" -> return GameOrigin.DIABLO
            "Yrel" -> return GameOrigin.WARCRAFT
            "Zagara" -> return GameOrigin.STARCRAFT
            "Zeratul" -> return GameOrigin.STARCRAFT
            "Zarya" -> return GameOrigin.OVERWATCH
            "Zuljin" -> return GameOrigin.WARCRAFT


            else -> return null
        }
    }

    fun mapRoleToImageRessource(role: RoleEnum): DrawableResource? {
        when (role) {
            RoleEnum.ranged -> return Res.drawable.ranged
            RoleEnum.support -> return Res.drawable.support
            RoleEnum.melee -> return Res.drawable.melee
            RoleEnum.heal -> return Res.drawable.heiler
            RoleEnum.tank -> return Res.drawable.tank
            RoleEnum.bruiser -> return Res.drawable.bruiser
        }
    }

    fun mapDifficultyToDrawable(difficulty: Difficulty): DrawableResource {
        return when (difficulty) {
            Difficulty.EASY -> Res.drawable.difficulty_easy
            Difficulty.MEDIUM -> Res.drawable.difficulty_medium
            Difficulty.HARD -> Res.drawable.difficulty_hard
            Difficulty.EXTREME -> Res.drawable.difficulty_extreme
        }
    }

    fun getPlatformType(): PlatformType {
        return when {
            getPlatform().name.contains("android", ignoreCase = true) -> PlatformType.ANDROID
            getPlatform().name.contains("web", ignoreCase = true) -> PlatformType.WEB
            else -> PlatformType.OTHER
        }
    }

    @Composable
    fun getResponsiveFontSize(): TextUnit {
        //TODO responsive
        return if (true) {
            12.sp
        } else if (false) {
            14.sp
        } else {
            16.sp // Ihre aktuelle fontSize
        }
    }
}