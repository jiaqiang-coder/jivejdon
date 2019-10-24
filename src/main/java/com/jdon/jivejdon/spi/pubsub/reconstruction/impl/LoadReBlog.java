/*
 * Copyright 2003-2009 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package com.jdon.jivejdon.pubsub.domain.consumer.read;

import java.util.ArrayList;
import java.util.Collection;

import com.jdon.annotation.Component;
import com.jdon.annotation.model.OnEvent;
import com.jdon.jivejdon.domain.model.ForumMessage;
import com.jdon.jivejdon.domain.model.util.OneManyDTO;
import com.jdon.jivejdon.infrastructure.repository.MessageRepository;

@Component
public class LoadReBlog {
	private MessageRepository messageRepository;

	public LoadReBlog(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	@OnEvent("loadReBlog")
	public OneManyDTO loadReBlogTos(Long messageId) {
		OneManyDTO oneManyDTO = null;
		try {
			ForumMessage messageFrom = messageRepository.getReBlogByTo(messageId);
			Collection<ForumMessage> messageTos = messageRepository.getReBlogByFrom(messageId);
			if (messageTos == null)
				return new OneManyDTO(messageFrom, new ArrayList());
			else
				return new OneManyDTO(messageFrom, messageTos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return oneManyDTO;
	}

}