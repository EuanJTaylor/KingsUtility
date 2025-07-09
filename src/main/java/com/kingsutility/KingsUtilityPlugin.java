package com.kingsutility;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.input.KeyListener;
import net.runelite.client.input.KeyManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import net.runelite.client.events.ConfigChanged;
import net.runelite.client.eventbus.Subscribe;

import javax.inject.Inject;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@PluginDescriptor(
		name = "Kings Utility",
		description = "Cycles through selected F1–F12 tabs using Tab",
		tags = {"tab", "hotkeys", "interface", "fkeys"}
)
public class KingsUtilityPlugin extends Plugin implements KeyListener
{
	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	@Inject
	private KeyManager keyManager;

	@Inject
	private KingsUtilityConfig config;

	private List<Integer> activeFunctionKeys;
	private int currentIndex = 0;

	@Override
	protected void startUp()
	{
		keyManager.registerKeyListener(this);
		updateActiveKeys();
	}

	@Override
	protected void shutDown()
	{
		keyManager.unregisterKeyListener(this);
		activeFunctionKeys = null;
	}

	private void updateActiveKeys()
	{
		activeFunctionKeys = new ArrayList<>();

		if (config.enableF1()) activeFunctionKeys.add(KeyEvent.VK_F1);
		if (config.enableF2()) activeFunctionKeys.add(KeyEvent.VK_F2);
		if (config.enableF3()) activeFunctionKeys.add(KeyEvent.VK_F3);
		if (config.enableF4()) activeFunctionKeys.add(KeyEvent.VK_F4);
		if (config.enableF5()) activeFunctionKeys.add(KeyEvent.VK_F5);
		if (config.enableF6()) activeFunctionKeys.add(KeyEvent.VK_F6);
		if (config.enableF7()) activeFunctionKeys.add(KeyEvent.VK_F7);
		if (config.enableF8()) activeFunctionKeys.add(KeyEvent.VK_F8);
		if (config.enableF9()) activeFunctionKeys.add(KeyEvent.VK_F9);

		// Reset index if out of bounds
		if (currentIndex >= activeFunctionKeys.size())
		{
			currentIndex = 0;
		}
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_TAB && activeFunctionKeys != null && !activeFunctionKeys.isEmpty())
		{
			e.consume(); // Prevent default Tab behavior

			int keyToSend = activeFunctionKeys.get(currentIndex);
			currentIndex = (currentIndex + 1) % activeFunctionKeys.size();

			clientThread.invoke(() ->
					client.getCanvas().dispatchEvent(new KeyEvent(
							client.getCanvas(),
							KeyEvent.KEY_PRESSED,
							System.currentTimeMillis(),
							0,
							keyToSend,
							KeyEvent.CHAR_UNDEFINED))
			);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Provides
	KingsUtilityConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(KingsUtilityConfig.class);
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		if (!event.getGroup().equals("kingsutility"))
		{
			return;
		}

		log.info("Config changed, updating active keys.");
		updateActiveKeys();
	}


}
