(ns planets.core.desktop-launcher
  (:require [planets.core :refer :all])
  (:import [com.badlogic.gdx.backends.lwjgl LwjglApplication]
           [org.lwjgl.input Keyboard])
  (:gen-class))

(defn -main
  []
  (LwjglApplication. planets "planets" 800 600)
  (Keyboard/enableRepeatEvents true))
