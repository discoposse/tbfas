watch:
	clj -m cljs.main \
			-w src/      \
			-c tbfas.core

compile:
	clj -m cljs.main \
			-O advanced  \
			-c tbfas.core

serve:
	clj -m cljs.main \
			-s
