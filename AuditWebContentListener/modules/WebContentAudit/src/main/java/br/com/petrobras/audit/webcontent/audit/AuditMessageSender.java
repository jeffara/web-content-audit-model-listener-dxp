package br.com.petrobras.audit.webcontent.audit;

import com.liferay.portal.kernel.audit.AuditException;
import com.liferay.portal.kernel.audit.AuditMessage;
import com.liferay.portal.kernel.audit.AuditMessageFactoryUtil;
import com.liferay.portal.kernel.audit.AuditRouterUtil;

import java.util.Date;

public class AuditMessageSender {

	public void sendAuditMessage(String eventType, long companyId, long userId, String userName, String className, String classPK, String message, Date timestamp) {
		
		//Prepara a mensagem para ser enviada
		AuditMessage auditMessage = AuditMessageFactoryUtil.getAuditMessageFactory().getAuditMessage(eventType, companyId, userId, userName, className, classPK, message, timestamp, null);
		
		//Realiza o envio da mensagem para a destination utilizando Liferay Message Bus
		try {
			AuditRouterUtil.route(auditMessage);
		} catch (AuditException e) {
			e.printStackTrace();
		}
	}
}
