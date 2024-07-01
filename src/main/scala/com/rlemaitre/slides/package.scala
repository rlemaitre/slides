package com.rlemaitre

import org.typelevel.literally.Literally

import scala.quoted.{Expr, Quotes}

package object slides:
    case object Absent
    type Maybe[T] = T | Absent.type
    extension [T](value: Maybe[T])
        inline def or(default: T): T = value match
            case Absent => default
            case v: T   => v
        end or

        inline def isAbsent: Boolean = value == Absent

        inline def isPresent: Boolean = !value.isAbsent

        inline def toOption: Option[T] = value match
            case Absent => None
            case v: T   => Some(v)
        end toOption

        inline def toList: List[T] = value match
            case Absent => Nil
            case v: T   => List(v)
        end toList

        inline def toSeq: Seq[T] = value match
            case Absent => Nil
            case v: T   => Seq(v)
        end toSeq

        inline def toSet: Set[T] = value match
            case Absent => Set.empty
            case v: T   => Set(v)
        end toSet

        inline def map[U](f: T => U): Maybe[U] = value match
            case Absent => Absent
            case v: T   => f(v)
        end map

        inline def flatMap[U](f: T => Maybe[U]): Maybe[U] = value match
            case Absent => Absent
            case v: T   => f(v)
        end flatMap

        inline def fold[U](ifAbsent: => U)(f: T => U): U = value match
            case Absent => ifAbsent
            case v: T   => f(v)
        end fold
    end extension

    extension (inline ctx: StringContext)
        inline def title(inline args: Any*): Title = ${ TitleLiteral('ctx, 'args) }

    object TitleLiteral extends Literally[Title]:
        override def validate(s: String)(using Quotes): Either[String, Expr[Title]] =
            if Title.rtc.test(s) then Right('{ Title.applyUnsafe(${ Expr(s) }) })
            else Left(Title.rtc.message)


end slides
