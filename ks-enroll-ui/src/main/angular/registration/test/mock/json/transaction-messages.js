'use strict';

angular.module('mockTransactionMessages', []).value('transactionMessages',
    [
        {
            "messageKey": "kuali.lpr.trans.message.credit.load.exceeded",
            "message": "Exceeded maximum credit limit <strong ng-if='maxCredits'>({{maxCredits}} credits)</strong>"
        },
        {
            "messageKey":"kuali.lpr.trans.message.time.conflict",
            "message":"Time conflict"
        },
        {
            "messageKey":"kuali.lpr.trans.message.waitlist.available",
            "message":"No seats available."
        },
        {
            "messageKey":"kuali.lpr.trans.message.waitlist.full",
            "message":"No seats available.<br/>(Waitlist full)"
        },
        {
            "messageKey":"kuali.lpr.trans.message.waitlist.not.offered",
            "message":"No seats available.<br/>(Waitlist not offered)"
        },
        {
            "messageKey":"kuali.lpr.trans.message.course.not.open",
            "message":"Registration is not currently open"
        },
        {
            "messageKey": "kuali.lpr.trans.message.drop.period.closed",
            "message": "Deadline for dropping has passed"
        },
        {
            "messageKey": "kuali.lpr.trans.message.edit.period.closed",
            "message": "Deadline for editing has passed"
        },
        {
            "messageKey":"kuali.lpr.trans.message.exception",
            "message":"There was an internal server error while processing your registration request."
        },
        {
            "messageKey":"kuali.lpr.trans.message.course.not.open",
            "message":"Registration is not currently open"
        },
        {
            "messageKey": "kuali.lpr.trans.message.course.grade.incomplete",
            "message": "{{courseCode}} has already been taken. Cannot repeat a course with a mark of 'I'."
        },
        {
            "messageKey": "kuali.lpr.trans.message.course.already.registered",
            "message": "You are already registered for this course."
        },
        {
            "messageKey": "kuali.lpr.trans.message.course.already.taken",
            "message": "{{courseCode}} has already been taken {{attempts | multiplicativeAdverb : ' times'}}. This course cannot be repeated more than {{maxRepeats | multiplicativeAdverb : ' times'}}."
        },
        {
            "messageKey": "kuali.lpr.trans.message.repeatability.warning",
            "message": "This will be your {{attempts + 1 | ordinal}} attempt of {{courseCode}}. This course cannot be attempted more than {{maxRepeats | multiplicativeAdverb : ' times'}}."
        },
        {
            "messageKey":"kuali.lpr.trans.item.message.exception",
            "message":"There was an internal server error while processing this item."
        }
    ]
);