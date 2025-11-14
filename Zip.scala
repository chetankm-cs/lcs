import Zip.Question

@scala.annotation.nowarn
object Zip extends App {

  sealed trait QuestionType
  case object SingleChoice extends QuestionType
  case object MultipleChoice  extends QuestionType
  case object NumberAnswer  extends QuestionType

  sealed trait CondType
  case object GT extends CondType
  case object LT  extends CondType
  case object EQ  extends CondType
  case object NEQ  extends CondType


  case class Form(list: List[Question])  {
    def getQuestion(id: Int): Option[Question] = {
      list.find(_.id == id)
    }

    def validateAll(): Unit = {
     list.foreach(_.validate())
    }
  }
  case class Answer(questionId: Int, value: String)
  case class AnswerForm(form: Form, answers: List[Answer]) {
    def getAnswer(questionId: Int): Option[Answer] = {
      answers.find(_.questionId == questionId)
    }

    def validate() = {
      form.list.forall { q => answers.exists(_.questionId == q.id) }
    }
  }

  case class Question(id: Int, qt: QuestionType, prompt: String, options: List[String] = List.empty) {
    def validate(): Unit = {
      qt match {
        case SingleChoice | MultipleChoice => require(options.nonEmpty)
        case NumberAnswer => require(options.isEmpty)
      }
    }
  }

  case class COND(questionId: Int, operation: CondType, value: String)

  def isConditionMet(condition: COND, answerForm: AnswerForm): Boolean = {
    val answer = answerForm.getAnswer(condition.questionId).get
    condition.operation match {
      case GT =>  condition.value.toInt < answer.value.toInt
      case LT => condition.value.toInt > answer.value.toInt
      case EQ =>  condition.value == answer.value
      case NEQ =>  condition.value != answer.value
    }
  }


  val questions = Form(List(
    Question(1, SingleChoice, "Say hello",  List("hello", "world")),
    Question(2, MultipleChoice, "Say hello and world",  List("hello", "world")),
    Question(3, NumberAnswer, "How old are you ? ")
  ))

  questions.validateAll()

  val answers = AnswerForm(questions, List(
    Answer(1, "hello"),
    Answer(2, "hello world"),
    Answer(3, "15")
  ))

  answers.validate()

  require(isConditionMet(COND(1, EQ, "hello"), answers), "answer should be helo")
  require(isConditionMet(COND(2, NEQ, "hello"), answers))
  require(isConditionMet(COND(3, LT, "65"), answers), "Age should be less then 65")
  require(isConditionMet(COND(3, GT, "18"), answers), "Age should be more then 18")


  // extend this to support and and or conditions

  trait ComplexCondition
  case class AND(op1: ComplexCondition, op2: ComplexCondition) extends ComplexCondition
  case class OR(op1: ComplexCondition, op2: ComplexCondition) extends ComplexCondition
  case class BASE(cond: COND) extends ComplexCondition

  def isComplexConditionMet(cc: ComplexCondition, answers: AnswerForm): Boolean = {
    cc match {
      case AND(op1, op2) => isComplexConditionMet(op1, answers) && isComplexConditionMet(op2, answers)
      case OR(op1, op2) => isComplexConditionMet(op1, answers) || isComplexConditionMet(op2, answers)
      case BASE(cond) =>  isConditionMet(cond, answers)
    }
  }

  require(
    isComplexConditionMet(
      AND(
        BASE(COND(1, EQ, "hello")),
        AND (
          BASE(COND(2, NEQ, "World")),
          BASE(COND(3, GT, "21"))
        )
      ), answers
    )
  )
}
