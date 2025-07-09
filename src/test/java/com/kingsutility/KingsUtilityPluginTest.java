package com.kingsutility;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class KingsUtilityPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(KingsUtilityPlugin.class);
		RuneLite.main(args);
	}
}