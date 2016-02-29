package elastic

import com.github.nscala_time.time.Imports._
import org.json4s._

case class ElvisPatientPlus(
  CareContactId: BigInt,
  DepartmentComment: String,
  Location: String,
  PatientId: BigInt,
  ReasonForVisit: String,
  Team: String,
  VisitId: BigInt,
  CareContactRegistrationTime: DateTime,
  VisitRegistrationTime: DateTime,
  // These are not included in data sent by TransformationService so they are initialized here
  RemovedTime: DateTime = DateTime.parse("0000-01-24T00:00:00Z"),
  TimeToDoctor: BigInt = -1,
  TimeToTriage: BigInt = -1,
  TotalTime:    BigInt = -1,
  Priority: String = "",

  // arrays
  Events: List[ElvisEvent],
  Updates: List[ElvisUpdateEvent]
)

case class ElvisEvent(
  CareEventId: BigInt,
  Category: String,
  End: DateTime,
  Start: DateTime,
  Title: String,
  Type: String,
  Value: String,
  VisitId: BigInt
)

/** Analogous for ElvisEvent, but for text field changes */
case class ElvisUpdateEvent(
  CareContactId: BigInt,
  PatientId: BigInt,
  VisitId: BigInt,
  Timestamp: DateTime ,
  ModifiedField: String,
  ModifiedTo : JValue
)

case class ElvisPatientDiff(updates: Map[String, JValue], newEvents: List[ElvisEvent], removedEvents: List[ElvisEvent])
case class NewPatient(timestamp: DateTime, patient: ElvisPatientPlus)
case class RemovedPatient(timestamp: DateTime, patient: ElvisPatientPlus)
case class SnapShot(patients: List[ElvisPatientPlus])
