/**
 * Copyright 2000-present Liferay, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.petrobras.audit.webcontent.modellistener;

import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import org.osgi.service.component.annotations.Component;

import br.com.petrobras.audit.webcontent.audit.AuditMessageSender;

@Component(
	immediate = true,
    service = ModelListener.class
)
public class WebcontentauditModelListener extends BaseModelListener<JournalArticle> {

	private AuditMessageSender auditMessageSender;
	
	@Override
	public void onAfterCreate(JournalArticle model) throws ModelListenerException {
//		System.out.println("Web Content ID: " + model.getArticleId() + "Added!");
		
		auditMessageSender = new AuditMessageSender();
		
		auditMessageSender.sendAuditMessage("ADD", model.getCompanyId(), model.getUserId(), model.getUserName(), JournalArticle.class.getName(), String.valueOf(model.getResourcePrimKey()), "Inclusão de Novo Conteúdo Web", model.getModifiedDate());
		
		auditMessageSender = null;
	}

	@Override
	public void onAfterUpdate(JournalArticle model) throws ModelListenerException {
//		System.out.println("Web Content ID: " + model.getArticleId() + " Updated!");
		
		auditMessageSender = new AuditMessageSender();
		
		auditMessageSender.sendAuditMessage("UPDATE", model.getCompanyId(), model.getUserId(), model.getUserName(), JournalArticle.class.getName(), String.valueOf(model.getResourcePrimKey()), "Atualização de Conteúdo Web", model.getModifiedDate());
		
		auditMessageSender = null;
	}
	
	@Override
	public void onAfterRemove(JournalArticle model) throws ModelListenerException {
//		System.out.println("Web Content ID: " + model.getArticleId() + " Deleted!");
		
		auditMessageSender = new AuditMessageSender();
		
		auditMessageSender.sendAuditMessage("DELETE", model.getCompanyId(), model.getUserId(), model.getUserName(), JournalArticle.class.getName(), String.valueOf(model.getResourcePrimKey()), "Remoção de Conteúdo Web", model.getModifiedDate());
		
		auditMessageSender = null;
	}
}