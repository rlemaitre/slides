package com.rlemaitre.slides

import com.raquo.laminar.api.L.*
import com.raquo.laminar.api.L.given
import com.rlemaitre.slides.Slide.BulletSlide
import com.rlemaitre.slides.Slide.TitleSlide
import io.github.iltotore.iron.*
import io.github.iltotore.iron.constraint.all.*
import org.scalajs.dom
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import typings.swiper.bundleMod.Swiper
import typings.swiper.typesModulesKeyboardMod.KeyboardOptions
import typings.swiper.typesModulesNavigationMod.NavigationOptions
import typings.swiper.typesModulesPaginationMod.PaginationOptions
import typings.swiper.typesSwiperOptionsMod.SwiperOptions

object Main:

    @JSImport("swiper/swiper-bundle.css")
    @js.native
    private def swiperStyles(): Unit = js.native

    def main(args: Array[String]): Unit =
        val container = dom.document.getElementById("app")
        val deck      = Deck(
          title = title"My Presentation",
          slides = List(
            TitleSlide(title"Hello, World!", "This is a simple slide."),
            BulletSlide(title"Bullet Slide", List("Bullet 1", "Bullet 2", "Bullet 3"))
          )
        )
        swiperStyles()
        render(
          container,
          MyComponent(deck)(
            width  := "100%",
            height := "100%"
          )
        )
        val swiper    = new Swiper(
          ".swiper",
          Swiper
              .extendedDefaults
              .setKeyboard(true)
              .setNavigation(NavigationOptions().setEnabled(true))
              .setPagination(PaginationOptions().setEnabled(true))
              .setSlidesPerView(1)
              .setCreateElements(true)
        )
    end main

    val nameVar = Var(initial = "world")

    def MyComponent(deck: Deck)(mods: Mod[HtmlElement]*): Element =
        div(
          cls    := "swiper",
          width  := "100%",
          height := "100%",
          div(
            cls    := "swiper-wrapper",
            width  := "100%",
            height := "100%",
            deck.slides.map:
                case TitleSlide(title, subtitle) =>
                    div(
                      cls := "swiper-slide",
                      h1(title),
                      subtitle.map(h2(_)).toOption
                    )

                case BulletSlide(title, bullets) =>
                    div(
                      cls := "swiper-slide",
                      h1(title.or("")),
                      ul(
                        bullets.map(li(_))
                      )
                    )

                case s =>
                    div(
                      cls := "swiper-slide",
                      h1(s"Unknown slide: $s")
                    )
          ),
          mods
        )
end Main
