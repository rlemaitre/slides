package com.rlemaitre.slides

import com.raquo.laminar.api.L.Element
import com.raquo.laminar.api.L.Image
import io.github.iltotore.iron.*
import io.github.iltotore.iron.constraint.all.*

opaque type Title <: String = String :| Not[Empty]
object Title extends RefinedTypeOps[String, Not[Empty], Title]

opaque type Syntax <: String = String :| Not[Empty]
object Syntax extends RefinedTypeOps[String, Not[Empty], Syntax]

case class CodeSnippet(language: Syntax, code: String)

enum Slide(title: Maybe[Title]):
    case TitleSlide(title: Title, subtitle: Maybe[String])                           extends Slide(title)
    case BasicSlide(title: Maybe[Title] = Absent)(content: Element)                  extends Slide(title)
    case BulletSlide(title: Maybe[Title], bullets: List[String])                     extends Slide(title)
    case CodeSlide(title: Maybe[Title], code: CodeSnippet)                           extends Slide(title)
    case ImageSlide(title: Maybe[Title], url: Image)                                 extends Slide(title)
    case ProfileSlide(title: Maybe[Title], name: String, role: String, image: Image) extends Slide(title)
    case CompanySlide(title: Maybe[Title], name: String, image: Image)               extends Slide(title)
    case ThankYouSlide(title: Maybe[Title])                                          extends Slide(title)
end Slide

case class Header()
case class Footer()

case class Deck(
    title: Title,
    slides: List[Slide]
)
