(ns planets.core
  (:require [play-clj.core :refer :all]
            [play-clj.ui :refer :all]
            [play-clj.g2d :refer :all]))

(def *scale* 1)

(defn update-position [entity]
  (if (= (:name entity) "Label")
    entity
    (assoc entity
      :orbit (+ (:orbit entity) (:orbit-step entity))
      :x (+ (* (:orbit-size entity) (Math/cos (:orbit entity)) *scale*) 350)
      :y (+ (* (:orbit-size entity) (Math/sin (:orbit entity)) *scale*) 300)
      :angle (+ (:angle entity) (:rotation-step entity)))))

(defscreen main-screen
  :on-show
  (fn [screen entities]
    (add-timer! screen :hello 0.3)
    (update! screen :renderer (stage) :camera (orthographic))
    (list
     (assoc (texture "Starfield.jpg")
       :name "Label"
       :x 10 :y 10 :width 800 :height 600)
     (assoc (texture "Sun.png")
       :x 50 :y 50 :width 40 :height 40
       :angle 30 :origen-x 20 :origen-y 20
       :name "Sun" :orbit 0.0 :orbit-size 0.0
       :orbit-step 0.0 :rotation-step 0.0)
     (assoc (texture "Mercury.jpg")
      :x 50 :y 50 :width 20 :height 20
      :angle 90 :origen-x 10 :origen-y 10
      :name "Mercury" :orbit 90.0 :orbit-size 58
      :orbit-step (/ 1 88.0) :rotation-step 1.0)
     (assoc (texture "Venus.jpg")
       :x 50 :y 50 :width 20 :height 20
       :angle 30 :origen-x 10 :origen-y 10
       :name "Venus" :orbit 30.0 :orbit-size 108
       :orbit-step (/ 1 224.7) :rotation-step -0.8)
     (assoc (texture "Earth.jpg")
       :x 50 :y 50 :width 20 :height 20
       :angle 0 :origen-x 10 :origen-y 10
       :name "Earth" :orbit 110.0 :orbit-size 150
       :orbit-step (/ 1 365.2) :rotation-step 10)
     (assoc (texture "Mars.jpg")
       :x 50 :y 50 :width 20 :height 20
       :angle 0 :origen-x 10 :origen-y 10
       :name "Mars" :orbit 220.0 :orbit-size 228
       :orbit-step (/ 1 687.0) :rotation-step 6)
     (assoc (texture "Jupiter.jpg")
       :x 50 :y 50 :width 30 :height 30
       :angle 0 :origen-x 15 :origen-y 15
       :name "Jupiter" :orbit 132.0 :orbit-size 778
       :orbit-step (/ 1 4332.0) :rotation-step 3)
     (assoc (texture "Saturn.jpg")
       :x 50 :y 50 :width 24 :height 24
       :angle 0 :origen-x 12 :origen-y 12
       :name "Saturn" :orbit 224.0 :orbit-size 1429
       :orbit-step (/ 1 10760.0) :rotation-step 1)
     (assoc (texture "Uranus.gif")
       :x 50 :y 50 :width 24 :height 24
       :angle 0 :origen-x 12 :origen-y 12
       :name "Uranus" :orbit 98.0 :orbit-size 2871
       :orbit-step (/ 1 30700.0) :rotation-step 6)
     (assoc (texture "Neptune.jpg")
       :x 50 :y 50 :width 24 :height 24
       :angle 0 :origen-x 12 :origen-y 12
       :name "Neptune" :orbit 67.9 :orbit-size 4504
       :orbit-step (/ 1 60200.0) :rotation-step 2)
     (assoc (label " Press any key to advance time. Left and Right change scale. All distances and times, but not sizes, are accurite." (color :white))
       :name "Label")))

  :on-render
  (fn [screen entities]
    (clear!)
    (render! screen entities))

  :on-resize
  (fn [screen entities]
    (height! screen 600))

  :on-key-down
  (fn [screen entities]
    (cond
     (= (:key screen) (key-code :dpad-left))
     (def *scale* (* *scale* 0.9))
     (= (:key screen) (key-code :dpad-right))
     (def *scale* (* *scale* (/ 10 9))))
    (map update-position entities))

  :on-timer
  (fn [screen entities]
    (add-timer! screen :hello 0.1)
    (map update-position entities)))

(defgame planets
  :on-create
  (fn [this]
    (set-screen! this main-screen)))
