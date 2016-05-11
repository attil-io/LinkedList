package io.attil;

import io.attil.impl.CallbackContext;

public interface WalkCallback {
	void processNode(CallbackContext ctx);
}
