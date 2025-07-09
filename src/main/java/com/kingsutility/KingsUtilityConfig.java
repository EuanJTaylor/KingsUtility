package com.kingsutility;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("kingsutility")
public interface KingsUtilityConfig extends Config
{
	@ConfigSection(
			name = "Tabs to Cycle",
			description = "Select which F1–F12 tabs to include in cycling",
			position = 0
	)
	String section = "tabs";

	@ConfigItem(
			keyName = "cycleF1",
			name = "Cycle F1 - Combat Styles",
			description = "Include F1 (Combat Styles) in the cycle",
			position = 1,
			section = section
	)
	default boolean enableF1() { return true; }

	@ConfigItem(
			keyName = "cycleF2",
			name = "Cycle F2 - Stats",
			description = "Include F2 (Stats) in the cycle",
			position = 2,
			section = section
	)
	default boolean enableF2() { return true; }

	@ConfigItem(
			keyName = "cycleF3",
			name = "Cycle F3 - Quests",
			description = "Include F3 (Quests) in the cycle",
			position = 3,
			section = section
	)
	default boolean enableF3() { return true; }

	@ConfigItem(
			keyName = "cycleF4",
			name = "Cycle F4 - Backpack",
			description = "Include F4 (Backpack) in the cycle",
			position = 4,
			section = section
	)
	default boolean enableF4() { return true; }

	@ConfigItem(
			keyName = "cycleF5",
			name = "Cycle F5 - Equipment",
			description = "Include F5 (Equipment) in the cycle",
			position = 5,
			section = section
	)
	default boolean enableF5() { return true; }

	@ConfigItem(
			keyName = "cycleF6",
			name = "Cycle F6 - Prayer",
			description = "Include F6 (Prayer) in the cycle",
			position = 6,
			section = section
	)
	default boolean enableF6() { return true; }

	@ConfigItem(
			keyName = "cycleF7",
			name = "Cycle F7 - Spells",
			description = "Include F7 (Spells) in the cycle",
			position = 7,
			section = section
	)
	default boolean enableF7() { return true; }

	@ConfigItem(
			keyName = "cycleF8",
			name = "Cycle F8 - Friends",
			description = "Include F8 (Friends) in the cycle",
			position = 8,
			section = section
	)
	default boolean enableF8() { return true; }

	@ConfigItem(
			keyName = "cycleF9",
			name = "Cycle F9 - Clan",
			description = "Include F9 (Clan) in the cycle",
			position = 9,
			section = section
	)
	default boolean enableF9() { return true; }

	// Optional: Remove this if unused, or hook it up to something
	default Runnable save()
	{
		return () -> {};
	}
}
