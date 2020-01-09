package controllers

import play.api.mvc.{Action, AnyContent, InjectedController}

final class HelloMeController
  extends InjectedController {

  def index: Action[AnyContent] = Action {
    Ok("Hello Me v3")
  }

}
